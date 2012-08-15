/**
 * 数据窗体
 */
function DataWindow(classDefine) {
    classDefine['queryForm'].fieldset = {};
    var actionBar = new ActionBar();
    actionBar.add(new ActionButton("", "", "查询", "formQuery"));
    actionBar.add(new ActionButton("", "", "重置", "formReset"));
    classDefine['queryForm'].actionBar = actionBar;

    var tableBar = new ActionBar();
    tableBar.add(new ActionButton("", "", "增加", ""));
    tableBar.add(new ActionButton("", "", "修改", ""));
    tableBar.add(new ActionButton("", "", "删除", ""));
    classDefine['classTable'].tableBar = tableBar;

    this.clazz = new DataClass(classDefine);
}

DataWindow.prototype = {
    toString: function() {
        return '<div class="easyui-layout" style="position: static;">' + getNorth(this.clazz) + getCenter(this.clazz) + '</div>';
    }
};

function getNorth(clazz) {
    return '<div region="north" split="false" border="false">' + clazz.queryForm.toString() + '</div>';
}

function getCenter(clazz) {
    return '<div region="center" split="false" border="false">' + clazz.dataTable.toString() + '</div>';
}

var $_grid;
var $_queryForm;

function formQuery() {
    $_grid.datagrid('reload', $_queryForm.serializeObject());
}

function formReset() {
    $_queryForm[0].reset();
}



jQuery.simpleWin = function(classDefine) {
    classDefine['queryForm'].fieldset = {};
    var actionBar = new ActionBar();
    actionBar.add(new ActionButton("", "", "查询", "formQuery"));
    actionBar.add(new ActionButton("", "", "重置", "formReset"));
    classDefine['queryForm'].actionBar = actionBar;

    var tableBar = [];
    tableBar.push({text : '增加', iconCls : 'icon-add', handler: null});
    tableBar.push('-');
    tableBar.push({text : '修改', iconCls : 'icon-cut', handler: null});
    tableBar.push('-');
    tableBar.push({text : '删除', iconCls : 'icon-remove', handler: null});

    var clazz = new DataClass(classDefine);
    var layout = $(getLayout());

    function getLayout() {
        return getNorth(clazz) + getCenter(clazz);
    }

    function getNorth(clazz) {
        return '<div region="north" split="false" border="false">' + clazz.queryForm.toString() + '</div>';
    }

    function getCenter(clazz) {
        return '<div region="center" split="false" border="false">' + clazz.dataTable.toString() + '</div>';
    }

    $('body').addClass('easyui-layout').append(layout).layout();

    $_queryForm = $('#' + clazz.queryForm.id);
    $_grid = $('#' + clazz.dataTable.id).datagrid({
        title : clazz.cname + '列表',
        url : contextPath + classRequestMapping + '/list',
        queryParams: $_queryForm.serializeObject(),
        pagination : true,
        rownumbers : true,
        singleSelect : true,
        striped:true,
        fit:true,
        toolbar: tableBar
    });

    return this;
};