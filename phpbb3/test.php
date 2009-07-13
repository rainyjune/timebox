<?php

/** 
*
* install script to set up permission options in the db for foo mod
* @license http://opensource.org/licenses/gpl-license.php GNU Public License 
*
*/

/**
* @ignore
*/

// initialize the page
define('IN_PHPBB', true);
define('IN_INSTALL', true);
$phpbb_root_path = (defined('PHPBB_ROOT_PATH')) ? PHPBB_ROOT_PATH : './';
$phpEx = substr(strrchr(__FILE__, '.'), 1);
include($phpbb_root_path . 'common.' . $phpEx);
include($phpbb_root_path . 'includes/functions_admin.' . $phpEx);
require($phpbb_root_path . 'includes/functions_module.' . $phpEx);


// Start session management
$user->session_begin();
$auth->acl($user->data);
$sql_string = 'select * from phpbb_tag_topic where tag_id = 1';

$result = $db->sql_query($sql_string);

trigger_error("nihoi");

?>