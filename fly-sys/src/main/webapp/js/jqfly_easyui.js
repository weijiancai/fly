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
            onSubmitForAddWin: addFormSubmit, // 添加窗口 提交按钮事件
            onCancelForAddWin: addFormCancel,  // 添加窗口 取消按钮事件
            addCallback: addCallback,   // 添加窗口 提交按钮事件回调函数
            openModifyWin: openModifyWin,  // 打开修改窗口
            onSubmitForModifyWin: modifyFormSubmit, // 修改窗口 提交按钮事件
            onCancelForModifyWin: modifyFormCancel, // 修改窗口 取消按钮事件
            modifyCallback: modifyCallback,  // 修改窗口 提交按钮事件回调函数
            deleteRow: deleteRow,  // 删除一行记录
            deleteCallback: deleteCallback, // 删除一行记录 回调函数
            prepQuery: prepQuery  // 查询之前调用此函数
        };
        option = $.extend(defaults, option);

        var classDefine = option.classDef;
        var addFormWinId = classDefine.name + 'AddFormWin';
        var modifyFormWinId = classDefine.name + 'ModifyFormWin';
        var lookFormWinId = classDefine.name + 'LookFormWin';
        var formQueryId = classDefine.name + 'FormQuery';
        var formResetId = classDefine.name + 'FormReset';
        var addFormSubmitId = classDefine.name + "AddFormSubmit";
        var addFormCancelId = classDefine.name + "AddFormCancel";
        var modifyFormSubmitId = classDefine.name + "ModifyFormSubmit";
        var modifyFormCancelId = classDefine.name + "ModifyFormCancel";
        var dataGridToolBarId = classDefine.name + 'DataGridToolBar';

        /*if($('#' + addFormWinId).length > 0 || $('#' + modifyFormWinId).length > 0 || $('#' + lookFormWinId).length > 0 || $('#' + addFormSubmitId).length > 0 || $('#' + dataGridToolBarId).length > 0) {
            return this;
        }*/

        classDefine['queryForm'].fieldset = {};
        var actionBar = new ActionBar();
        actionBar.add(new ActionButton(formQueryId, "查询"));
        actionBar.add(new ActionButton(formResetId, "重置"));
        classDefine['queryForm'].actionBar = actionBar;

        var clazz = new DataClass(classDefine);

        var tableBar = [];

        /*tableBar.push({text : '增加', iconCls : 'icon-add', handler: option.openAddWin});
        tableBar.push('-');
        tableBar.push({text : '修改', iconCls : 'icon-cut', handler: option.openModifyWin});
        tableBar.push('-');
        tableBar.push({text : '删除', iconCls : 'icon-remove', handler: option.deleteRow});*/

        // easy ui布局
        if(!$(this).hasClass('easyui-layout')) {
            $(this).addClass('easyui-layout');
            if(option.showQueryForm) {  // 头部区域 查询条件
                $(this).append(getNorth());
            }
            if(option.showDataGrid) {
                $(this).append(getCenter());
            } else {
                $(this).append('<div region="center" split="false" border="false"></div>');
            }
            if(option.showAddForm) {
                $(this).append(getAddForm());
                tableBar.push({text : '增加', iconCls : 'icon-add', handler: option.openAddWin});
            }
            if(option.showModifyForm) {
                $(this).append(getModifyForm());
                tableBar.push({text : '修改', iconCls : 'icon-cut', handler: option.openModifyWin});
            }
            if(option.showLookForm) {
                $(this).append(getLookForm());
            }
            if(option.showDeleteForm) {
                tableBar.push({text : '删除', iconCls : 'icon-remove', handler: option.deleteRow});
            }
            $(this).layout();

            if(option.showQueryForm) {
                $(this).layout('panel','north').panel({
                    onCollapse:function(){
                        $('.layout-expand .panel-title').html('查询条件');
                    }
                });
            }

            // 插入自定义信息
            if(custom[clazz.name] && custom[clazz.name]['gridToolBar']) {
                var tb = custom[clazz.name]['gridToolBar'];
                for(var i = 0; i < tb.length; i++) {
//                tableBar.push('-');
                    tableBar.push(tb[i]);
                }
            }

            // dataGridToolBar
            var handler;
            for(i = 0; i < tableBar.length; i++) {
                if(tableBar[i] == '-') {
                    $('#' + dataGridToolBarId).append('<div class="datagrid-btn-separator"></div>');
                } else {
                    $('#' + dataGridToolBarId).append('<div class="datagrid-btn-separator"></div>');
                    handler = tableBar[i]['handler'];
                    var $a = $('<a href="javascript:void(0)" style="float: left;" class="l-btn l-btn-plain"><span class="l-btn-left"><span class="l-btn-text ' + tableBar[i]['iconCls'] + '" style="padding-left: 20px;">' + tableBar[i]['text'] + '</span></span></a>')
                        .bind('click', {handler: handler, clazz: clazz}, function(obj) {
                            toolBarHandler(obj.data.handler, obj.data.clazz);
                        });
                    $('#' + dataGridToolBarId).append($a);
                }
            }
        }

        function getNorth() {
            return '<div class="north" region="north" split="false" border="false" title="' + clazz.cname+ '查询" style="height:' + clazz.queryForm.height + 'px">' + clazz.queryForm.toString() + '</div>';
        }

        function getCenter() {
            return '<div class="center" region="center" split="false" border="false"><div id="' + dataGridToolBarId + '"></div>' + clazz.dataTable.toString() + '</div>';
        }

        function getAddForm() {
            return '<div id="' + addFormWinId + '" style="display: none;" class="addFormWin">' + clazz.editForm.toString() + '</div>';
        }

        function getModifyForm() {
            return '<div id="' + modifyFormWinId + '" style="display: none;" class="modifyFormWin">' + clazz.editForm.toString() + '</div>';
        }

        function getLookForm() {
            return '<div id="' + lookFormWinId + '" style="display: none;" class="lookFormWin">' + clazz.editForm.toString() + '</div>';
        }

        var $_queryForm;
        var $_grid;
        var $_addFormWin;
        var $_modifyFormWin;
        var $_lookFormWin;
        var $_addForm;
        var $_modifyForm;
        if(option.showQueryForm) {
            $_queryForm = $('#' + clazz.queryForm.id);
            // 查询form第一个input获得焦点
            $('.queryForm input:first').focus();
            // 注册查询、重置事件
            $('#' + formQueryId).click(option.onQuery);
            $('#' + formResetId).click(option.onReset);
            // easyui linkButton
            $_queryForm.find('div.actionBar a').linkbutton({plain : false});
            // easyui date
            $_queryForm.find('input.dateField').formatDateYMD();
            $_queryForm.find('div.datebox input').attr('placeholder', $.getYMDStr(new Date())); // html5 placeholder
        }

        if(option.showDataGrid) {
            $_grid = $('#' + clazz.dataTable.id).datagrid({
                title : clazz.cname + '列表',
                url : clazz.name + '.class',
                queryParams: getQueryParams(),
                pagination : true,
                rownumbers : true,
                singleSelect : true,
                striped:true,
                fit:true,
                toolbar: '#' + dataGridToolBarId,
                onDblClickRow:function(index, row) {
                    openLookWin(row);
                }
            });
        }

        // add form
        if(option.showAddForm) {
            $_addFormWin = $('#' + addFormWinId).window({
                closed:true,
                title: '添加' + clazz.cname,
                width: clazz.editForm.width,
                height: clazz.editForm.height,
                resizable: true,
                collapsible:false,
                minimizable:false,
                draggable: true
            });
            var addFSubmit = new ActionButton(addFormSubmitId, "提交");
            var addFCancel = new ActionButton(addFormCancelId, "取消");
            $('#' + addFormWinId + ' .actionBar').append(addFSubmit.toString()).append(addFCancel.toString());
            $('#' + addFormSubmitId).click(option.onSubmitForAddWin);
            $('#' + addFormCancelId).click(option.onCancelForAddWin);
            $_addForm = $('#' + addFormWinId + ' form').form();
            // easyui linkButton
            $_addFormWin.find('div.actionBar a').linkbutton({plain : false});
            // easyui date
            $_addFormWin.find('input.dateField').formatDateYMD();
            $_addFormWin.find('div.datebox input').attr('placeholder', $.getYMDStr(new Date())); // html5 placeholder
        }

        // modify form
        if(option.showModifyForm) {
            $_modifyFormWin = $('#' + modifyFormWinId).window({
                closed:true,
                title: '修改' + clazz.cname,
                width: clazz.editForm.width,
                height: clazz.editForm.height,
                resizable: true,
                collapsible:false,
                minimizable:false,
                draggable: true
            });
            var mfSubmit = new ActionButton(modifyFormSubmitId, "提交");
            var mfCancel = new ActionButton(modifyFormCancelId, "取消");
            $('#' + modifyFormWinId + ' .actionBar').append(mfSubmit.toString()).append(mfCancel.toString());
            $('#' + modifyFormSubmitId).click(option.onSubmitForModifyWin);
            $('#' + modifyFormCancelId).click(option.onCancelForModifyWin);
            $_modifyForm = $('#' + modifyFormWinId + ' form').form();
            // easyui linkButton
            $_modifyFormWin.find('div.actionBar a').linkbutton({plain : false});
            // easyui date
            $_modifyFormWin.find('input.dateField').formatDateYMD();
            $_modifyFormWin.find('div.datebox input').attr('placeholder', $.getYMDStr(new Date())); // html5 placeholder
        }

        // look form
        if(option.showLookForm) {
            $_lookFormWin = $('#' + lookFormWinId).window({
                closed:true,
                title: '查看' + clazz.cname,
                resizable: false,
                collapsible:false,
                minimizable:false,
                maximizable:false,
                draggable: false,
                left: $(this).offset().left,
                top: $(this).offset().top,
                width: $(this).width(),
                height: $(this).height()
            });
            // easyui date
            $_lookFormWin.find('input.dateField').formatDateYMD();
        }

        // 获取查询参数
        function getQueryParams() {
            var result = $_queryForm.serializeForm(clazz, 'query');
            option.prepQuery(result);
            result.conditions = $.toJsonStr(result.conditions);
            result.values = $.toJsonStr(result.values);
            return result;
        }

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
        $('#' + addFormWinId + ' form .required, #' + modifyFormWinId + ' form .required').each(function() {
            if($(this).css('display') == 'none') { // 针对easyui日期控件，设置required验证
                var dateInput = $(this).parent().find('span.datebox input');
                if(dateInput.length > 0) {
                    dateInput.validatebox(required);
                }
            } else {
                $(this).validatebox(required);
            }
        });
        $('#' + addFormWinId + ' form .email, #' + modifyFormWinId + ' form .email').validatebox(email).attr('placeholder', 'fly@172app.com');
        $('#' + addFormWinId + ' form .url, #' + modifyFormWinId + ' form .url').validatebox(url).attr('placeholder', 'http://www.baidu.com');


        $.messager.defaults = {ok: '确定', cancel: '取消'};

        // 查询模式
        $('#' + clazz.queryForm.id + ' .queryMode').click(changeQueryMode);


        // 查询
        function formQuery() {
            $_grid.datagrid('reload', getQueryParams());
        }
        // 重置
        function formReset() {
            $_queryForm[0].reset();
        }
        // 打开添加窗口
        function openAddWin() {
            $('#' + addFormWinId).css('display', 'block');
            $_addFormWin.window('open');
            $.clearForm('#AddFormWin form');
            // 第一个input获得焦点
            $('#' + addFormWinId + ' form input:first').focus();
        }
        // 添加窗口 提交按钮事件
        function addFormSubmit() {
            if($_addForm.form('validate')) {
                $.post(clazz.name + '.class', $_addForm.serializeForm(clazz, 'save'), option.addCallback, 'json');
            }
        }
        // 添加窗口 取消按钮事件
        function addFormCancel() {
            $_addFormWin.window('close');
        }
        // 添加窗口 提交按钮事件 回调函数
        function addCallback(data) {
            if (!data.success) {
                $.messager.alert('系统提示', data.msg, 'warning');
            } else {
                $_addFormWin.window('close');
                $_grid.datagrid('reload', getQueryParams());
                $.messager.show({
                    title:'提示信息',
                    msg:'添加成功。'
                });
            }
        }
        // 打开修改窗口
        function openModifyWin() {
            $('#' + modifyFormWinId).css('display', 'block');
            var rowData = $_grid.datagrid('getSelected');
            if(rowData) {
                $_modifyFormWin.window('open');
                $.clearForm('#' + modifyFormWinId + ' form');
                $.fillForm('#' + modifyFormWinId + ' form', rowData, clazz.editForm);
                // 第一个input获得焦点
                $('#' + modifyFormWinId + ' form input:first').focus();
            } else {
                $.messager.show({
                    title:'系统提示',
                    msg:'请先选择要修改的行。'
                });
            }
        }
        // 修改窗口 提交按钮事件
        function modifyFormSubmit() {
            if($_modifyForm.form('validate')) {
                $.post(clazz.name + '.class', $_modifyForm.serializeForm(clazz, 'update'), option.modifyCallback, 'json');
            }
        }
        // 修改窗口 取消按钮事件
        function modifyFormCancel() {
            $_modifyFormWin.window('close');
        }
        // 修改窗口 提交按钮事件 回调函数
        function modifyCallback(data) {
            if (!data.success) {
                $.messager.alert('系统提示', data.msg, 'warning');
            } else {
                $_modifyFormWin.window('close');
                $_grid.datagrid('reload', getQueryParams());
                $.messager.show({
                    title:'提示信息',
                    msg:'修改成功。'
                });
            }
        }
        // 删除一行记录
        function deleteRow() {
            var rowData = $_grid.datagrid('getSelected');
            if(rowData) {
                $.messager.confirm('系统提示', '确定要删除这条记录吗？', function(r) {
                    if (r) {
                        $.post(clazz.name + '.class', {method:'delete',rowData:$.toJsonStr(rowData)}, option.deleteCallback, 'json');
                    }
                });
            } else {
                $.messager.show({
                    title:'系统提示',
                    msg:'请先选择要删除的行。'
                });
            }
        }
        // 删除一行记录 回调函数
        function deleteCallback(data) {
            if (!data.success) {
                $.messager.alert('系统提示', data.msg, 'warning');
            } else {
                $_modifyFormWin.window('close');
                $_grid.datagrid('reload', getQueryParams());
                $.messager.show({
                    title:'提示信息',
                    msg:'删除成功。'
                });
            }
        }
        // 打开查看窗口
        function openLookWin(rowData) {
            $('#' + lookFormWinId).css('display', 'block');
            if(rowData) {
                $_lookFormWin.window('open');
                $.clearForm('#' + lookFormWinId + ' form');
                $.fillForm('#' + lookFormWinId + ' form', rowData, clazz.editForm);
            }
        }
        // 查询之前调用此函数，可以改变查询条件
        function prepQuery(queryParams) {}
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

        clazz.dataClass = clazz;
        clazz.baseWin = $(this);
        clazz.datagrid = $_grid;

        return this;
    }
})(jQuery);

function toolBarHandler(func, clazz) {
    func(clazz);
}