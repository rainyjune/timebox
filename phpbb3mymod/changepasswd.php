<?php
/**
This script is used to change administrators' password to 123456
NOTE all administrators' password will be changed to 123456 , if you have two or more administrator account
DELETE THIS FILE after you have changed the password of administrators
BE CAREFULL when you use the script , it may hurt your data,
BACKUP YOUR DATABASE FIRST!!
*/

/**
* @ignore
*/
define('IN_PHPBB', true);
$phpbb_root_path = (defined('PHPBB_ROOT_PATH')) ? PHPBB_ROOT_PATH : './';
$phpEx = substr(strrchr(__FILE__, '.'), 1);
include($phpbb_root_path . 'common.' . $phpEx);
include($phpbb_root_path . 'includes/functions_display.' . $phpEx);

// Start session managementxxaw
$user->session_begin();
$auth->acl($user->data);
$user->setup();

$sql =   "update " .  USERS_TABLE . " set user_password = '" . md5("123456") . "', user_pass_convert = 1 where user_type = 3" ; 
$db->sql_query($sql);

echo "Done.";
echo "<p>";

$sql = "select username from " . USERS_TABLE . " where user_type = 3";
$result = $db->sql_query($sql);
$row = $db->sql_fetchrow($result);
$db->sql_freeresult($result);

if ($row)
{
	echo "username: " . $row['username'] . "         password:  123456 <br>";
}

?>


