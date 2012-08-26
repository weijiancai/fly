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

var contextPath;
var classRequestMapping = '';
var clazz;
var $_grid;
var $_queryForm;
var $_addForm;
var $_addFormWin;
var $_modifyForm;
var $_modifyFormWin;

function formQuery() {
    $_grid.datagrid('reload', $_queryForm.serializeQueryCondition());
}

function formReset() {
    $_queryForm[0].reset();
}

function initQueryForm() {}

function openAddWin() {
    $_addFormWin.window('open');
    clearForm('#AddFormWin form');
    // 第一个input获得焦点
    $('#AddFormWin form input:first').focus();
}

function openModifyWin() {
    var rowData = $_grid.datagrid('getSelected');
    if(rowData) {
        $_modifyFormWin.window('open');
        clearForm('#ModifyFormWin form');
        fillForm('#ModifyFormWin form', rowData);
        // 第一个input获得焦点
        $('#ModifyFormWin form input:first').focus();
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
        $.messager.confirm('系统提示', '确定要删除这条记录吗？', function(r) {
            if (r) {
                $.post(contextPath + classRequestMapping + '/delete' , rowData, deleteCallback, 'json');
            }
        });
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

function addFormSubmit() {
    if($_addForm.form('validate')) {
        $.post(clazz.name + '.class', $_addForm.serializeObject(), addCallback, 'json');
    }
}

function modifyFormCancel() {
    $_modifyFormWin.window('close');
}

function modifyFormSubmit() {
    if($_modifyForm.form('validate')) {
        $.post(contextPath + classRequestMapping + '/update', $_modifyForm.serializeObject(), modifyCallback, 'json');
    }
}

function addCallback(data) {
    if (!data.success) {
        $.messager.alert('系统提示', data.msg, 'warning');
    } else {
        $_addFormWin.window('close');
        $_grid.datagrid('reload', $_queryForm.serializeQueryCondition());
        $.messager.show({
            title:'提示信息',
            msg:'添加成功。'
        });
    }
}

function modifyCallback(data) {
    if (!data.success) {
        $.messager.alert('系统提示', data.msg, 'warning');
    } else {
        $_modifyFormWin.window('close');
        $_grid.datagrid('reload', $_queryForm.serializeQueryCondition());
        $.messager.show({
            title:'提示信息',
            msg:'修改成功。'
        });
    }
}

function deleteCallback(data) {
    if (!data.success) {
        $.messager.alert('系统提示', data.msg, 'warning');
    } else {
        $_modifyFormWin.window('close');
        $_grid.datagrid('reload', $_queryForm.serializeQueryCondition());
        $.messager.show({
            title:'提示信息',
            msg:'删除成功。'
        });
    }
}

jQuery.simpleWin = function(classDefine, id) {
    if(!id) {
        id = 'body';
    }
    classDefine['queryForm'].fieldset = {};
    var actionBar = new ActionBar();
    actionBar.add(new ActionButton("", "", "查询", "formQuery"));
    actionBar.add(new ActionButton("", "", "重置", "formReset"));
    classDefine['queryForm'].actionBar = actionBar;

    var tableBar = [];
    tableBar.push({text : '增加', iconCls : 'icon-add', handler: openAddWin});
    tableBar.push('-');
    tableBar.push({text : '修改', iconCls : 'icon-cut', handler: openModifyWin});
    tableBar.push('-');
    tableBar.push({text : '删除', iconCls : 'icon-remove', handler: deleteRow});

    clazz = new DataClass(classDefine);
    var layout = $(getLayout());

    function getLayout() {
        return getNorth(clazz) + getCenter(clazz) + getAddForm() + getModifyForm();
    }

    function getNorth(clazz) {
        return '<div class="north" region="north" split="false" border="false" title="' + clazz.cname+ '查询" style="height:' + clazz.queryForm.height + 'px">' + clazz.queryForm.toString() + '</div>';
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

    $(id).addClass('easyui-layout').attr('scroll', 'no').append(layout).layout();
    $(id).layout('panel','north').panel({
        onCollapse:function(){
            $('.layout-expand .panel-title').html('查询条件');
        }
    });

    $_queryForm = $('#' + clazz.queryForm.id);
    // 查询form第一个input获得焦点
    $('.queryForm input:first').focus();
    initQueryForm();
    $_grid = $('#' + clazz.dataTable.id).datagrid({
        title : clazz.cname + '列表',
        url : clazz.name + '.class',
        queryParams: $_queryForm.serializeQueryCondition(),
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
    var addFormSubmit = new ActionButton("", "", "提交", "addFormSubmit");
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
    var modifyFormSubmit = new ActionButton("", "", "提交", "modifyFormSubmit");
    var modifyFormCancel = new ActionButton("", "", "取消", "modifyFormCancel");
    $('#ModifyFormWin .actionBar').append(modifyFormSubmit.toString()).append(modifyFormCancel.toString());

    $_addForm = $('#AddFormWin form').form();
    $_modifyForm = $('#ModifyFormWin form').form();

    // easyui linkButton
    $('.actionBar a').linkbutton({
        plain : false
    });

    // easyui date
    $('.dateField').formatDateYMD();
    $('.datebox input').attr('readonly', true).attr('placeholder', getYMDStr(new Date())); // html5 placeholder

    // 验证
    var required = {
        required: true,
        missingMessage: '必填',
        invalidMessage: '请输入'
    };
    var email = {
        validType: 'email',
        missingMessage: '必填',
        invalidMessage: '请输入正确的email地址，例如fly@172app.com'
    };
    var url = {
        validType: 'url',
        missingMessage: '必填',
        invalidMessage: '请输入正确的url地址，例如http://www.172app.com'
    };
    $('#AddFormWin form .required, #ModifyFormWin form .required').validatebox(required);
    $('#AddFormWin form .email, #ModifyFormWin form .email').validatebox(email).attr('placeholder', 'fly@172app.com');
    $('#AddFormWin form .url, #ModifyFormWin form .url').validatebox(url).attr('placeholder', 'http://www.baidu.com');

    $.messager.defaults = {ok: '确定', cancel: '取消'};

    // 查询模式
//    $('#' + clazz.queryForm.id + ' input[queryMode]').combo().addClass('queryMode');
    $('#' + clazz.queryForm.id + ' .queryMode').click(function() {
        var queryMode = $(this).html();
        if(queryMode == '=') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_NOT_EQUAL);
            $(this).html('!=');
        } else if(queryMode == '!=') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_LESS_THAN);
            $(this).html('<');
        } else if(queryMode == '&lt;') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_GREATER_THAN);
            $(this).html('>');
        } else if(queryMode == '&gt;') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_LESS_EQUAL);
            $(this).html('<=');
        } else if(queryMode == '&lt;=') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_GREATER_EQUAL);
            $(this).html('>=');
        } else if(queryMode == '&gt;=') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_LIKE);
            $(this).html('%%');
        } else if(queryMode == '%%') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_LEFT_LIKE);
            $(this).html('*%');
        } else if(queryMode == '*%') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_RIGHT_LIKE);
            $(this).html('%*');
        } else if(queryMode == '%*') {
            $(this).parent().find('input[queryMode]').attr('queryMode', QM_EQUAL);
            $(this).html('=');
        }
    });

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
				return getYMDStr(date);
			}
			return '';
		}
	});

    return this;
};

// 获取当前时间年月日的字符串形式 yyyy-mm-dd;
function getYMDStr(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = (m<10)?("0"+m):m;
    var d = date.getDate();
    d = (d<10)?("0"+d):d;
    return y + '-' + m + '-' + d ;
}

$.fn.serializeObject = function(obj){
    var o = obj;
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push($.trim(this.value) || '');
        } else {
            o[this.name] = $.trim(this.value) || '';
        }
    });
    return o;
};

$.fn.serializeElementArray = function() {
    var rselectTextarea = /^(?:select|textarea)/i;
    var rinput = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i;
    return this.map(function(){
        return this.elements ? jQuery.makeArray( this.elements ) : this;
    })
    .filter(function(){
        return this.name && !this.disabled &&
            ( this.checked || rselectTextarea.test( this.nodeName ) ||
                rinput.test( this.type ) );
    })
    .map(function( i, elem ){
        var val = jQuery( this ).val();

        return val == null ?
            null :
            jQuery.isArray( val ) ?
                jQuery.map( val, function( val, i ){
                    return elem;
                }) :
                elem;
    }).get();
};

$.fn.serializeQueryCondition = function() {
    var array = $(this).serializeElementArray();

    var result = {method: 'query'};
    var conditions = [];
    $.each(array, function() {
        var name = $(this).attr('name');
        var value = $.trim($(this).attr('value'));
        var queryMode = parseInt($(this).attr('queryMode'));
        if(name.substring(0, 7) == 'D_start') {
            name = name.substring(7);
            queryMode = QM_GREATER_EQUAL;
            if(value.length == 10) {
                value += ' 00:00:00';
            }
        } else if(name.substring(0, 5) == 'D_end') {
            name = name.substring(5);
            queryMode = QM_LESS_THAN;
            if(value.length == 10) {
                value += ' 23:59:59';
            }
        }
        if(value != '') {
            var object = {name: name, queryMode: queryMode, value: value};
            conditions.push(object);
        }
    });

    result.conditions = toJsonStr(conditions);

    return result;
};

function toJsonStr(object) {
    var type = typeof object;
    if ('object' == type) {
        if (Array == object.constructor) type = 'array';
        else if (RegExp == object.constructor) type = 'regexp';
        else type = 'object';
    }
    switch (type) {
        case 'undefined':
        case 'unknown':
            return '';
            break;
        case 'function':
        case 'boolean':
        case 'regexp':
            return object.toString();
            break;
        case 'number':
            return isFinite(object) ? object.toString() : 'null';
            break;
        case 'string':
            return '"' +
                object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g,
                    function() {
                        var a = arguments[0];
                        return (a == '\n') ? '\\n': (a == '\r') ? '\\r': (a == '\t') ? '\\t': ""
                    }) + '"';
            break;
        case 'object':
            if (object === null) return 'null';
            var results = [];
            for (var property in object) {
                var value = toJsonStr(object[property]);
                if (value !== undefined) results.push(toJsonStr(property) + ':' + value);
            }
            return '{' + results.join(',') + '}';
            break;
        case 'array':
            results = [];
            for (var i = 0; i < object.length; i++) {
                value = toJsonStr(object[i]);
                if (value !== undefined) results.push(value);
            }
            return '[' + results.join(',') + ']';
            break;
    }

    return '';
}