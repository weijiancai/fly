/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-5-9
 * Time: 下午10:51
 * To change this template use File | Settings | File Templates.
 */
$(function() {
    // 获取项目信息
    $.post("projectDefine.class", function(data) {
        alert(data);
    })
});