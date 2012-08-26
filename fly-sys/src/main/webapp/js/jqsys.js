/**
 * fly系统 常量定义脚本文件
 *
 * User: weijiancai
 * Date: 2012-8-25 23:08
 */

custom = {
    ClassDefine: {
        gridToolBar: [
            {text : '字段信息', iconCls : 'icon-add', handler: fieldHandler},
            {text : '查询表单', iconCls : 'icon-add', handler: queryFormHandler},
            {text : '编辑表单', iconCls : 'icon-add', handler: editFormHandler},
            {text : '数据表格', iconCls : 'icon-add', handler: dataGridHandler}
        ]
    }
};

var ClassField;
var ClassForm;
var ClassFormField;

$(function() {
    $('body').append('<div id="FieldWin" style="height: 100%"></div>');
    $('body').append('<div id="QueryFormWin" style="height: 100%"></div>');

    $.post('ClassField.class', function(clazz) {
        ClassField = clazz;
    });
    $.post('ClassForm.class', function(clazz) {
        ClassForm = new DataClass(clazz);
    });
    $.post('ClassFormField.class', function(clazz) {
        ClassFormField = new DataClass(clazz);
    });
});

function fieldHandler(masterClazz) {
    var rowData = masterClazz.datagrid.datagrid('getSelected');
    if(rowData) {
        $('#FieldWin').baseWin({
            classDef: ClassField,
            showQueryForm: false,
            prepQuery: function(params) {
                params.conditions.push({name: 'class_id', queryMode: QM_EQUAL, value: rowData.id});
            }
        });

        var $_fieldWin = $('#FieldWin').window({
            closed:true,
            title: '字段信息',
            resizable: true,
            collapsible:false,
            minimizable:false,
            maximizable:false,
            draggable: true,
            left: masterClazz.baseWin.offset().left,
            top: masterClazz.baseWin.offset().top,
            width: masterClazz.baseWin.width(),
            height: masterClazz.baseWin.height()
        });

        $_fieldWin.window('open');
    } else {
        $.messager.show({
            title:'系统提示',
            msg:'请先选择行。'
        });
    }
}
function queryFormHandler(masterClazz) {
    var rowData = masterClazz.datagrid.datagrid('getSelected');
    if(rowData) {
        $('#MasterDiv').html(ClassForm.editForm.toString());
        $('#ItemDiv').html(ClassFormField.editForm.toString());
        var queryCondition = new QueryCondition();
        queryCondition.addCondition('class_id', QM_EQUAL, rowData.id);
        queryCondition.addCondition('form_type', QM_EQUAL, '0');

        $.post('ClassForm.class', queryCondition.toString(), function(data) {
            $.fillForm('#MasterDiv form', data.rows[0], ClassForm.editForm);
        });

        $.post(rowData.name + '.class', function(clazz) {
            $('#QueryFormWin').baseWin({
                classDef: clazz,
                showDataGrid: false,
                showAddForm: false,
                showModifyForm: false,
                showLookForm: false,
                showDeleteForm: false
            });

            var $_fieldWin = $('#QueryFormWin').window({
                closed:true,
                title: '查询表单信息',
                resizable: true,
                collapsible:false,
                minimizable:false,
                maximizable:false,
                draggable: true,
                left: masterClazz.baseWin.offset().left,
                top: masterClazz.baseWin.offset().top,
                width: masterClazz.baseWin.width(),
                height: masterClazz.baseWin.height()
            });

            $_fieldWin.window('open');
        });
    } else {
        $.messager.show({
            title:'系统提示',
            msg:'请先选择行。'
        });
    }
}
function editFormHandler() {

}

function dataGridHandler() {

}