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

var clazz;
var $_grid;
var $_queryForm;
var $_addForm;
var $_addFormWin;
var $_modifyForm;
var $_modifyFormWin;

function formQuery() {
    $_grid.datagrid('reload', $_queryForm.serializeObject());
}

function formReset() {
    $_queryForm[0].reset();
}

function initQueryForm() {}

function add() {
    $_addFormWin.window('open');
    clearForm('#AddFormWin form');
}

function modify() {
    var rowData = $_grid.datagrid('getSelected');
    if(rowData) {
        $_modifyFormWin.window('open');
            clearForm('#ModifyFormWin form');
            fillForm('#ModifyFormWin form', rowData);
    } else {
        $.messager.show({
            title:'系统提示',
            msg:'请先选择要修改的行。'
        });
    }
}

function deleteRow() {
    var rowData = $_grid.datagrid('getSelected');
    if(rowData) {

    } else {
        $.messager.show({
            title:'系统提示',
            msg:'请先选择要删除的行。'
        });
    }
}

function addFormCancel() {
    $_addFormWin.window('close');
}

function modifyFormCancel() {
    $_modifyFormWin.window('close');
}

jQuery.simpleWin = function(classDefine) {
    classDefine['queryForm'].fieldset = {};
    var actionBar = new ActionBar();
    actionBar.add(new ActionButton("", "", "查询", "formQuery"));
    actionBar.add(new ActionButton("", "", "重置", "formReset"));
    classDefine['queryForm'].actionBar = actionBar;

    var tableBar = [];
    tableBar.push({text : '增加', iconCls : 'icon-add', handler: add});
    tableBar.push('-');
    tableBar.push({text : '修改', iconCls : 'icon-cut', handler: modify});
    tableBar.push('-');
    tableBar.push({text : '删除', iconCls : 'icon-remove', handler: deleteRow});

    clazz = new DataClass(classDefine);
    var layout = $(getLayout());

    function getLayout() {
        return getNorth(clazz) + getCenter(clazz) + getAddForm() + getModifyForm();
    }

    function getNorth(clazz) {
        return '<div class="north" region="north" split="false" border="false">' + clazz.queryForm.toString() + '</div>';
    }

    function getCenter(clazz) {
        return '<div class="center" region="center" split="false" border="false">' + clazz.dataTable.toString() + '</div>';
    }

    function getAddForm() {
        return '<div id="AddFormWin" style="display: block;">' + clazz.editForm.toString() + '</div>';
    }

    function getModifyForm() {
        return '<div id="ModifyFormWin" style="display: block;">' + clazz.editForm.toString() + '</div>';
    }

    $('body').addClass('easyui-layout').append(layout).layout();

    $_queryForm = $('#' + clazz.queryForm.id);
    $_addForm = $('#' + clazz.editForm.id);
    initQueryForm();
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

    // add form
    $_addFormWin = $('#AddFormWin').window({
        closed:true,
        title: '添加' + clazz.cname,
        width: clazz.editForm.width,
        height: clazz.editForm.height,
        resizable: true,
        collapsible:false,
        minimizable:false,
        draggable: true
    });
    var addFormSubmit = new ActionButton("", "", "提交", "formQuery");
    var addFormCancel = new ActionButton("", "", "取消", "addFormCancel");
    $('#AddFormWin .actionBar').append(addFormSubmit.toString()).append(addFormCancel.toString());

    // modify form
    $_modifyFormWin = $('#ModifyFormWin').window({
        closed:true,
        title: '修改' + clazz.cname,
        width: clazz.editForm.width,
        height: clazz.editForm.height,
        resizable: true,
        collapsible:false,
        minimizable:false,
        draggable: true
    });
    var modifyFormSubmit = new ActionButton("", "", "提交", "formQuery");
    var modifyFormCancel = new ActionButton("", "", "取消", "modifyFormCancel");
    $('#ModifyFormWin .actionBar').append(modifyFormSubmit.toString()).append(modifyFormCancel.toString());

    // easyui linkButton
    $('.actionBar a').linkbutton({
        plain : false
    });

    // easyui date
    $('.dateField').formatDateYMD();
    $('.datebox input').attr('readonly', true);

    return this;
};

/**
 * 填充form数据
 */
function fillForm(formId, data) {
    var objForm = $(formId)[0];
    if(objForm == null || objForm == undefined) return false;
    var elements = objForm.elements;
    for(var i=0; i<elements.length; i++)
    {
        var name = elements[i].name;
        var type = elements[i].type;
        var tag = elements[i].tagName.toLowerCase();

        if(data[name]== null||data[name]=='null'){
            data[name] = '';
        }

        if(type == "text" || type == "password" || tag == 'textarea' || type == "hidden")
        {
            elements[i].value = data[name];
        }
        //当对象为下拉列表时，清除时置为第一个值
        else if(tag=="select"){
            for(var m = 0; m< elements[i].options.length; m++){
                if(elements[i].options[m].value == data[name]){
                    elements[i].options[m].selected=true;
                }
            }
        }
        else if(type == "radio" && elements[i].value == data[name]){
            elements[i].checked = true;
        }
        else if(type == "checkbox"){
            var a = data[name].split(',');
            for(var j=0; j< a.length; j++){
                if(a[j] == elements[i].value){
                    elements[i].checked = true;
                }
            }
        }
    }
    return false;
}

/**
 * 清除Form中文本框的内容，如果是只读属性则不清除。
 * Param: objForm Fomr对象
 */
function clearForm(formId) {
    var objForm = $(formId)[0];
    if(objForm == null || objForm == undefined) return false;
    var elements = objForm.elements;
    for(var i=0; i<elements.length; i++)
    {
        var type = elements[i].type;
        var tag = elements[i].tagName.toLowerCase();
        if((type == "text" || type == "password" || tag == 'textarea' || type == "hidden") && !elements[i].readOnly)
        {
            elements[i].value = "";
        }
        //当对象为下拉列表时，清除时置为第一个值
        else if(tag=="select" && !elements[i].disabled){
            elements[i].options[0].selected=true;
        }
        else if((type == "checkbox" || type == "radio") && !elements[i].disabled ){
            elements[i].checked = false;
        }
    }

    return false;
}

$.fn.formatDateYMD=function() {
	this.datebox( {
		currentText : '今天',
		closeText : '关闭',
		disabled : false,
		required : false,
		needTime:true,
		showTime:true,
		formatter : function(date){
			if (date instanceof Date) {
				var y = date.getFullYear();
				var m = date.getMonth() + 1;
				m = (m<10)?("0"+m):m;
				var d = date.getDate();
				d = (d<10)?("0"+d):d;
				return y + '-' + m + '-' + d ;
			}
			return '';
		}
	});

    return this;
};