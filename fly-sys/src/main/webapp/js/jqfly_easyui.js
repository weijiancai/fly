/**
 * fly系统 基于jquery easyui
 *
 * User: weijiancai
 * Date: 2012-8-23 11:57
 */
(function($) {
    /**
     * 基本窗体
     */
    $.fn.baseWin = function(option) {
        var defaults = {
            classDef: null,  // 类定义信息
            showQueryForm: true, // 是否显示查询表单
            showDataGrid: true,  // 是否显示数据表格
            showAddForm: true,  // 是否显示添加表单
            showModifyForm: true,  // 是否显示修改表单
            showDeleteForm: true,  // 是否显示删除表单
            showLookForm: true,  // 是否显示查看表单
            onQuery: formQuery,  // 查询事件
            onReset: formReset,  //查询表单重置
            openAddWin: openAddWin,  // 打开添加窗口
            onSubmitForAddWin: null, // 添加窗口 提交按钮事件
            onCancelForAddWin: null,  // 添加窗口 取消按钮事件
            addCallback: addCallback,   // 添加窗口 提交按钮事件回调函数
            openModifyWin: openModifyWin,  // 打开修改窗口
            onSubmitForModifyWin: null, // 修改窗口 提交按钮事件
            onCancelForModifyWin: null, // 修改窗口 取消按钮事件
            modifyCallback: modifyCallback,  // 修改窗口 提交按钮事件回调函数
            openDeleteWin: deleteRow,  // 删除一行记录
            deleteCallback: deleteCallback, // 删除一行记录 回调函数
            prepQuery: null  // 查询之前调用此函数
        };
        option = $.extend(defaults, option);

        var classDefine = option.classDef;
        var queryForm;
        var addForm;
        var modifyForm;
        var dataTable;

        var clazz = new DataClass(classDefine);

        // easy ui布局
        if(!$(this).hasClass('easyui-layout')) {
            $(this).addClass('easyui-layout');
            if(option.showQueryForm) {  // 头部区域 查询条件
                var north = $('<div class="north" region="north" split="false" border="false" title="' + clazz.cname+ '查询"></div>');
                $(this).append(north);
                queryForm = north.queryForm({dataClass:clazz,onQuery:option.onQuery,prepQuery:option.prepQuery});
            }
            if(option.showDataGrid) {
                var center = $('<div region="center" split="false" border="false" style="width:100%;height:100%;"></div>');
                $(this).append(center);
                dataTable = center.dataTable({
                    dataClass: clazz,
                    openAddWin: option.openAddWin,
                    openModifyWin: option.openModifyWin,
                    openDeleteWin: option.openDeleteWin,
                    queryParams: queryForm.getQueryParams(),
                    lookWin: {
                        top: queryForm.form.offset().top,
                        left: queryForm.form.offset().left,
                        width: $(this).width() + 20,
                        height: $(this).height()
                    }
                });
            } else {
                $(this).append('<div region="center" split="false" border="false"></div>');
            }
            if(option.showAddForm) {
                addForm = $(this).dataForm({
                    dataClass: clazz,
                    onSubmit: option.onSubmitForAddWin,
                    onCancel: option.onCancelForAddWin,
                    onCallback: option.addCallback,
                    type: 'add'
                });
            }
            if(option.showModifyForm) {
                modifyForm = $(this).dataForm({
                    dataClass: clazz,
                    onSubmit: option.onSubmitForModifyWin,
                    onCancel: option.onCancelForModifyWin,
                    onCallback: option.modifyCallback,
                    type: 'update'
                });
            }

            $(this).layout();

            if(option.showQueryForm) {
                $(this).layout('panel','north').panel({
                    onCollapse:function(){
                        $('.layout-expand .panel-title').html('查询条件');
                    }
                });
            }
        }

        var $_grid;

        if(option.showDataGrid) {
            $_grid = dataTable.grid;
        }

        // 查询
        function formQuery() {
            $_grid.datagrid('reload', queryForm.getQueryParams());
        }
        // 重置
        function formReset() {
            queryForm.form[0].reset();
        }
        // 打开添加窗口
        function openAddWin() {
            addForm.open();
        }
        // 添加窗口 提交按钮事件 回调函数
        function addCallback(data) {
            formCallback(data, addForm, "添加成功。");
        }
        // 打开修改窗口
        function openModifyWin() {
            openWin(function(rowData) {
                modifyForm.open(rowData);
            }, '请先选择要修改的行。');
        }

        // 修改窗口 提交按钮事件 回调函数
        function modifyCallback(data) {
            formCallback(data, modifyForm, "修改成功。");
        }
        // 删除一行记录
        function deleteRow() {
            openWin(function(rowData) {
                $.messager.confirm('系统提示', '确定要删除这条记录吗？', function(r) {
                    if (r) {
                        $.post(clazz.name + '.class', {method:'delete',rowData:$.toJsonStr(rowData)}, option.deleteCallback, 'json');
                    }
                });
            }, '请先选择要删除的行。');
        }
        // 删除一行记录 回调函数
        function deleteCallback(data) {
            formCallback(data, null, "删除成功。");
        }
        // 打开窗口
        function openWin(callback, errorMsg) {
            var rowData = $_grid.datagrid('getSelected');
            if(rowData) {
                callback(rowData);
            } else {
                $.messager.show({
                    title:'系统提示',
                    msg:'请先选择要删除的行。'
                });
            }
        }
        // 表单提交， 回调函数
        function formCallback(data, form, successMsg) {
            if (!data.success) {
                $.messager.alert('系统提示', data.msg, 'warning');
            } else {
                if(form) {
                    form.close();
                }
                $_grid.datagrid('reload', queryForm.getQueryParams());
                $.messager.show({
                    title:'提示信息',
                    msg: successMsg
                });
            }
        }

        clazz.dataClass = clazz;
        clazz.baseWin = $(this);
        clazz.datagrid = $_grid;

        return this;
    };

    // 查询表单
    $.fn.queryForm = function(option) {
        var defaults = {
            dataClass: null,  // 类定义信息
            onQuery: null,  // 查询事件
            onReset: formReset,  //查询表单重置
            prepQuery: null  // 查询之前调用此函数
        };
        option = $.extend(defaults, option);

        var clazz = option.dataClass;
        var formQueryId = clazz.name + 'FormQuery';
        var formResetId = clazz.name + 'FormReset';

        clazz.queryForm.fieldset = {};
        var actionBar = new ActionBar();
        actionBar.add(new ActionButton(formQueryId, "查询"));
        actionBar.add(new ActionButton(formResetId, "重置"));
        clazz.queryForm.actionBar = actionBar;
        // 增加查询表单
        $(this).append(clazz.queryForm.toString());

        var $_queryForm = $(this).find('form');
        // 查询form第一个input获得焦点
        $_queryForm.find('input:first').focus();
        // 注册查询、重置事件
        $('#' + formQueryId).click(option.onQuery);
        $('#' + formResetId).click(option.onReset);
        // easyui linkButton
        $_queryForm.find('div.actionBar a').linkbutton({plain : false});
        // easyui date
        $_queryForm.find('input.dateField').formatDateYMD();
        $_queryForm.find('span.datebox input').attr('placeholder', $.getYMDStr(new Date())); // html5 placeholder

        function formReset() {
            $_queryForm[0].reset();
        }

        // 查询模式
        $('#' + clazz.queryForm.id + ' .queryMode').click(changeQueryMode);

        return {
            form: $_queryForm,
            getQueryParams: function () {
                var result = $_queryForm.serializeForm(clazz, 'query');
                if(option.prepQuery) {
                    option.prepQuery(result);
                }
                result.conditions = $.toJsonStr(result.conditions);
                result.values = $.toJsonStr(result.values);
                return result;
            }
        };
    };

    // 数据表单： 增加、修改
    $.fn.dataForm = function(option) {
        var defaults = {
            dataClass: null,
            onSubmit: formSubmit,  // 表单提交
            onCancel: formCancel, // 表单取消
            onCallback: formCancel, // 表单提交后的回调函数
            type: 'add'  // 表单类型，add 增加表单， edit 编辑表单
        };

        option = extend(defaults, option);
        var clazz = option.dataClass;
        var addFormWinId = clazz.name + 'AddFormWin';
        var addFormSubmitId = clazz.name + "AddFormSubmit";
        var addFormCancelId = clazz.name + "AddFormCancel";
        var modifyFormWinId = clazz.name + 'ModifyFormWin';
        var modifyFormSubmitId = clazz.name + "ModifyFormSubmit";
        var modifyFormCancelId = clazz.name + "ModifyFormCancel";
        var formWinId = addFormWinId;
        var formTitle = '添加';
        var formSubmitId = addFormSubmitId;
        var formCancelId = addFormCancelId;

        if(option.type == 'add') {
            formWinId = addFormWinId;
            formTitle = '添加';
            formSubmitId = addFormSubmitId;
            formCancelId = addFormCancelId;
        } else if(option.type == 'update') {
            formWinId = modifyFormWinId;
            formTitle = '修改';
            formSubmitId = modifyFormSubmitId;
            formCancelId = modifyFormCancelId;
        }

        var actionBar = new ActionBar();
        actionBar.add(new ActionButton(formSubmitId, "提交"));
        actionBar.add(new ActionButton(formCancelId, "取消"));
        clazz.editForm.actionBar = actionBar;

        $(this).append('<div id="' + formWinId + '" style="display: none;">' + clazz.editForm.toString() + '</div>');

        var $_form = $('#' + formWinId + ' form').form();

        var $_formWin = $('#' + formWinId).window({
            closed:true,
            title: formTitle + clazz.cname,
            width: clazz.editForm.width,
            height: clazz.editForm.height,
            resizable: true,
            collapsible:false,
            minimizable:false,
            draggable: true
        });

        $('#' + formSubmitId).click(option.onSubmit);
        $('#' + formCancelId).click(option.onCancel);

        // easyui linkButton
        $_formWin.find('div.actionBar a').linkbutton({plain : false});
        // easyui date
        $_formWin.find('input.dateField').formatDateYMD();
        $_formWin.find('div.datebox input').attr('placeholder', $.getYMDStr(new Date())); // html5 placeholder

        // 验证
        addFormValidator(formWinId);

        // 表单窗口 提交按钮事件
        function formSubmit() {
            if($_form.form('validate')) {
                $.post(clazz.name + '.class', $_form.serializeForm(clazz, option.type, true), option.onCallback, 'json');
            }
        }
        // 窗口 取消按钮事件
        function formCancel() {
            $_formWin.window('close');
        }

        return {
            form: $_form,
            open: function (data) { // 打开添加窗口
                $('#' + formWinId).css('display', 'block');
                $_formWin.window('open');
                $.clearForm('#' + formWinId + ' form');
                $.fillForm('#' + formWinId + ' form', data, clazz.editForm);
                // 第一个input获得焦点
                $('#' + formWinId + ' form input:first').focus();
            },
            close: function() { // 关掉窗口
                $_formWin.window('close');
            }
        }
    };

    // 数据表格
    $.fn.dataTable = function(option) {
        var defaults = {
            dataClass: null,  // 类定义信息
            showAddForm: true,  // 是否显示添加表单
            showModifyForm: true,  // 是否显示修改表单
            showDeleteForm: true,  // 是否显示删除表单
            showLookForm: true,  // 是否显示查看表单
            openAddWin: null,  // 打开添加窗口
            openModifyWin: null,  // 打开修改窗口
            openDeleteWin: null,  // 打开删除窗口
            queryParams: '',  // 查询参数
            lookWin: {
                left: $(this).offset().left,
                top: $(this).offset().top,
                width: $(this).width() + 20,
                height: $(this).height()
            }
        };

        option = $.extend(defaults, option);

        var clazz = option.dataClass;
        var tableBar = [];
        var dataGridToolBarId = clazz.name + 'DataGridToolBar';
        var lookFormWinId = clazz.name + 'LookFormWin';
        var $_lookFormWin;
        var $_toolBar = $('<div id="' + dataGridToolBarId + '"></div>');

        // 增加数据表格
        $(this).append($_toolBar).append(clazz.dataTable.toString());

        // look form
        if(option.showLookForm) {
            $_lookFormWin = $('<div id="' + lookFormWinId + '" style="display: none;" class="lookFormWin">' + clazz.editForm.toString() + '</div>');
            $(this).append($_lookFormWin);
            $_lookFormWin.window({
                closed:true,
                title: '查看' + clazz.cname,
                resizable: false,
                collapsible:false,
                minimizable:false,
                maximizable:false,
                draggable: false,
                left: option.lookWin.left,
                top: option.lookWin.top,
                width: option.lookWin.width,
                height: option.lookWin.height
            });
            // easyui date
            $_lookFormWin.find('form input.dateField').formatDateYMD();
        }

        if(option.showAddForm) {
            tableBar.push({text : '增加', iconCls : 'icon-add', handler: option.openAddWin});
        }
        if(option.showModifyForm) {
            tableBar.push({text : '修改', iconCls : 'icon-cut', handler: option.openModifyWin});
        }
        if(option.showDeleteForm) {
            tableBar.push({text : '删除', iconCls : 'icon-remove', handler: option.deleteRow});
        }

        // 插入自定义DataGrid Toolbar信息
        if(custom[clazz.name] && custom[clazz.name]['gridToolBar']) {
            var tb = custom[clazz.name]['gridToolBar'];
            for(var i = 0; i < tb.length; i++) {
                tableBar.push(tb[i]);
            }
        }

        // dataGridToolBar
        var handler;
        for(i = 0; i < tableBar.length; i++) {
            if(tableBar[i] == '-') {
                $_toolBar.append('<div class="datagrid-btn-separator"></div>');
            } else {
                $_toolBar.append('<div class="datagrid-btn-separator"></div>');
                handler = tableBar[i]['handler'];
                var $a = $('<a href="javascript:void(0)" style="float: left;" class="l-btn l-btn-plain"><span class="l-btn-left"><span class="l-btn-text ' + tableBar[i]['iconCls'] + '" style="padding-left: 20px;">' + tableBar[i]['text'] + '</span></span></a>')
                    .bind('click', {handler: handler, clazz: clazz}, function(obj) {
                        toolBarHandler(obj.data.handler, obj.data.clazz);
                    });
                $_toolBar.append($a);
            }
        }

        var $_grid = $('#' + clazz.dataTable.id).datagrid({
            title : clazz.cname + '列表',
            url : clazz.name + '.class',
            queryParams: option.queryParams,
            pagination : true,
            rownumbers : true,
            singleSelect : true,
            striped:true,
            fit:true,
            toolbar: '#' + dataGridToolBarId,
            onDblClickRow:function(index, row) {
                if(option.showLookForm) {
                    openLookWin(row);
                }
            }
        });

        // 打开查看窗口
        function openLookWin(rowData) {
            $('#' + lookFormWinId).css('display', 'block');
            if(rowData) {
                $_lookFormWin.window('open');
                $.clearForm('#' + lookFormWinId + ' form');
                $.fillForm('#' + lookFormWinId + ' form', rowData, clazz.editForm);
            }
        }

        return {
            grid: $_grid
        };
    };
})(jQuery);

function toolBarHandler(func, clazz) {
    func(clazz);
}

// 改变查询模式
function changeQueryMode() {
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
}

// 添加表单验证信息
function addFormValidator(id) {
    $('#' + id + ' form .required').each(function() {
        if($(this).css('display') == 'none') { // 针对easyui日期控件，设置required验证
            var dateInput = $(this).parent().find('span.datebox input');
            if(dateInput.length > 0) {
                dateInput.validatebox(CK_REQUIRED);
                dateInput.attr('id', $(this).attr('id'));
            }
        } else {
            $(this).validatebox(CK_REQUIRED);
        }
    });
    $('#' + id + ' form .email').validatebox(CK_EMAIL).attr('placeholder', 'fly@172app.com');
    $('#' + id + ' form .url').validatebox(CK_URL).attr('placeholder', 'http://www.baidu.com');
}

function extend(src, dest) {
    for(var property in dest) {
        if(dest[property]) {
            src[property] = dest[property];
        }
    }
    return src;
}