<?php
/**
 * @author hellojinjie
 * @date Jul 2, 2009
 */

/**
 * @ignore
 */
if (!defined('IN_PHPBB'))
{
	exit;
}

/**
 * mcp_logs
 * Handling warning the users
 * @package mcp
 */
class mcp_tags
{
	var $u_action;
	var $p_master;

	function mcp_logs(&$p_master)
	{
		$this->p_master = &$p_master;
	}

	function main($id, $mode)
	{
		global $auth, $db, $user, $template;
		global $config, $phpbb_root_path, $phpEx;

		$this->tpl_name = 'mcp_tags';
		$this->page_title = '主题标签';

		$action = request_var('action', '');

		switch ($mode)
		{
			case 'front':
				switch ($action)
				{
					case 'del':
						$tag_id_del = request_var('tag_id', 0);
						$forum_id = request_var('forum_id', 0);

						// 如果没有这个forum的版主权限.说明这个数据是人为构造并提交的,
						// 这时只要直接exit就行了,不给出任何的出错信息
						if (!$auth->acl_get('m_', $forum_id))
						{
							exit();
						}

						if (confirm_box(true))
						{
							// delete the spicific tag
							if ($tag_id_del == 0)
							{
								break;
							}

							$db->sql_transaction('begin');

							$sql_string = "delete from phpbb_tags where id = '$tag_id_del'";
							$db->sql_query($sql_string);

							$sql_string = "delete from phpbb_tag_topic where tag_id = '$tag_id_del'";
							$db->sql_query($sql_string);

							$db->sql_transaction('commit');
						}
						else
						{
							confirm_box(false, '真的要删除这个标签吗?', build_hidden_fields(array(
								'tag_id'	=> $tag_id_del,
								'mode'		=> $mode,					
								'i'			=> $id,
								'forum_id'	=> $forum_id,
								'action'	=> $action))
							);
						}
						break;
					case 'add':
						$tag_name = $db->sql_escape(request_var('tag_name', '', true));
						$forum_id = request_var('forum_id', 0);

						if ($tag_name == '' || $forum_id == 0)
						{
							break;
						}

						// 如果没有这个forum的版主权限.说明这个数据是人为构造并提交的,
						// 这时只要直接exit就行了,不给出任何的出错信息
						if (!$auth->acl_get('m_', $forum_id))
						{
							exit();
						}

						$sql_string = "insert into phpbb_tags (tag_name, used_times, forum_id, type, tag_icon_id) values (" .
								"'$tag_name'" . ", 0 , '$forum_id', 2 , 0)";
						$db->sql_query($sql_string);

						break;
					case 'quxiaojinghua':
						$forum_id = request_var('f', 0);
						$topic_id = request_var('t', 0);
						
						$sql_string = "delete from phpbb_tag_topic where tag_id = 1 and topic_id = $topic_id";
						$db->sql_query($sql_string);
						
						$sql_string = "update phpbb_topics set icon_id = 0 where topic_id = $topic_id";
						$db->sql_query($sql_string);
						
						$meta_info = append_sid("{$phpbb_root_path}viewtopic.$phpEx", "f=$forum_id&amp;t=$topic_id");
						$message = sprintf($user->lang['RETURN_TOPIC'], '<a href="' . $meta_info . '">', '</a>');


						meta_refresh(3, $meta_info);
						$message .= '<br /><br />' . '<a href="' . append_sid("{$phpbb_root_path}viewforum.$phpEx", "f=$forum_id&amp;t=$topic_id") . '">'.'回到版面'.'</a>';
						trigger_error($message);
						break;
					case 'jinghua':
						$forum_id = request_var('f', 0);
						$topic_id = request_var('t', 0);
						
						$sql_string = "insert into phpbb_tag_topic (tag_id, topic_id) values (1, $topic_id)";
						$db->sql_query($sql_string);
						
						$sql_string = "update phpbb_topics set icon_id = 11 where topic_id = $topic_id";
						$db->sql_query($sql_string);
						
						$meta_info = append_sid("{$phpbb_root_path}viewtopic.$phpEx", "f=$forum_id&amp;t=$topic_id");
						$message = sprintf($user->lang['RETURN_TOPIC'], '<a href="' . $meta_info . '">', '</a>');


						meta_refresh(3, $meta_info);
						$message .= '<br /><br />' . '<a href="' . append_sid("{$phpbb_root_path}viewforum.$phpEx", "f=$forum_id&amp;t=$topic_id") . '">'.'回到版面'.'</a>';
						trigger_error($message);
						break;
					case 'list':
					default :
				}
				break;
		}
		$this->list_tags();
	}

	/**
	 * 得到的论坛列表,一个数组,
	 * 不包括分区,
	 * @return unknown_type
	 * 我不可不可以用一个sql就完成这个功能呢,这样这个函数就没有存在的必要了
	 */
	function list_forums()
	{
		global $auth, $db, $user, $template;
		global $config, $phpbb_root_path, $phpEx;

		$sql_string = 'select forum_id, forum_name
				from phpbb_forums 
				where right_id - left_id = 1 
				order by forum_id';

		$result = $db->sql_query($sql_string);

		while ($row = $db->sql_fetchrow($result))
		{
			$forum_list[$row['forum_id']] = $row['forum_name'];
		}
		$db->sql_freeresult($result);

		/*
		 $p_forums = $auth->acl_getf('m_', true);

		 foreach ($p_forums as $p_forum_id => $forum)
		 {
		 if ($forum['m_'])
		 {
		 if (array_key_exist($forum_list, $p_forum_id))
		 {
		 $p_forum_list[$p_forum_id]
		 }
		 }
		 }
		 */
		return $forum_list;
	}

	/**
	 * 列出主题标签
	 * 做全部的显示工作
	 * @return unknown_type
	 */
	function list_tags()
	{
		global $auth, $db, $user, $template;
		global $config, $phpbb_root_path, $phpEx;

		// 这里我们得到具有tag的forum的列表
		$sql_string = 'select f.forum_id, f.forum_name, t.id as tag_id, t.tag_name
							 from phpbb_forums as f 
							 left join phpbb_tags as t       
							 on f.forum_id = t.forum_id 
							 where f.forum_type = 1';
		$result = $db->sql_query($sql_string);
		while ($row = $db->sql_fetchrow($result))
		{
			$forum_tags[$row['forum_id']][] = array('forum_name' => $row['forum_name'], 'tag_id' => $row['tag_id'], 'tag_name' => $row['tag_name'], 'forum_id' => $row['forum_id']);
		}
		$db->sql_freeresult($result);

		// 看看你在哪几个forum有版主权限
		$forum_moderator = $auth->acl_getf('m_', true);

		// http://wiki.phpbb.com/Template.assign_block_vars#Example_.232_Nested_blocks
		foreach ($forum_tags as $key => $tags_val)
		{
			$template->assign_block_vars('tags', array(
								'FORUM_NAME' => $tags_val[0]['forum_name'], // 这种得到forum_name的方式也太暴力了	
								'MODERATOR'  => $forum_moderator[$key]['m_'],
								'HAS_TAG'	 => $tags_val[0]['tag_id'] == null ? false : true,
								'FORUM_ID'	 => $key,
			));

			foreach ($tags_val as $val)
			{
				$template->assign_block_vars('tags.val', array(
										'FORUM_ID'	=>	$val['forum_id'],
										'TAG_ID'	=>	$val['tag_id'],
										'TAG_NAME'	=>	$val['tag_name'],
				));
			}
		}

	}
}

?>