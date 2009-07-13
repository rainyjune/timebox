<?php
/**
*
* @package mcp
* @version $Id: mcp_logs.php 8479 2008-03-29 00:22:48Z naderman $
* @copyright (c) 2005 phpBB Group
* @license http://opensource.org/licenses/gpl-license.php GNU Public License
*
*/

/**
* @package module_install
*/
class mcp_tags_info
{
	function module()
	{
		return array(
			'filename'	=> 'mcp_tags',
			'title'		=> '主题标签',
			'version'	=> '1.0.0',
			'modes'		=> array(
				'front'			=> array('title' => '首页', 'auth' => 'acl_m_ || aclf_m_', 'cat' => array('MCP_主题标签'))
			),
		);
	}

	function install()
	{
	}

	function uninstall()
	{
	}
}

?>