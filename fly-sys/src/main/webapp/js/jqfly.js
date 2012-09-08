/**
 * fly系统 基于jquery
 *
 * User: weijiancai
 * Date: 2012-8-23 11:57
 */
(function($) {
    $.messager.defaults = {ok: '确定', cancel: '取消'};

    /**
     * 查询表单
     *
     * @param classDefine 类定义信息
     */
    $.fn.queryForm = function(classDefine) {

    };

    $.fn.serializeFormObject = function(obj){
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

    $.fn.serializeForm = function(classDef, method, isConvertToJsonStr) {
        var array = $(this).serializeElementArray();

        var result = {method: method};
        var conditions = [];
        var values = {};
        var colName,tableName;
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
                if('query' == method) {
                    var object = {name: classDef.colNameMap[name], queryMode: queryMode, value: value};
                    conditions.push(object);
                } else if('add' == method || 'update' == method) {
                    colName = classDef.colNameMap[name];
                    tableName = colName.split('.')[0];
                    if(values[tableName]) {
                        values[tableName][colName] = value;
                    } else {
                        var obj = {};
                        obj[colName] = value;
                        values[tableName] = obj;
                    }
                }
            }
        });

        result.conditions = isConvertToJsonStr ? $.toJsonStr(conditions) : conditions;
        result.values = isConvertToJsonStr ? $.toJsonStr(values) : values;

        return result;
    };

    /**
     * 填充form数据
     */
    $.fn.fillForm = function(data, editForm) {
        if(!data) {
            return;
        }
        var objForm = $(this)[0];
        if(objForm == null || objForm == undefined) return false;
        var elements = objForm.elements;
        for(var i=0; i<elements.length; i++)
        {
            var name = elements[i].name;
            var type = elements[i].type;
            var tag = elements[i].tagName.toLowerCase();
            var value = editForm.getValue(name, data[name]);

            if(value == null|| value == 'null'){
                value = '';
            }

            if(type == "text" || type == "password" || tag == 'textarea' || type == "hidden")
            {
                elements[i].value = value;
                // 针对easyui日期控件input值的设置
                if($(elements[i]).parent().hasClass('datebox')) {
                    $(elements[i]).parent().find('input').val(value);
                }
            }
            //当对象为下拉列表时，清除时置为第一个值
            else if(tag=="select"){
                for(var m = 0; m< elements[i].options.length; m++){
                    if(elements[i].options[m].value == value){
                        elements[i].options[m].selected=true;
                    }
                }
            }
            else if(type == "radio" && elements[i].value == value){
                elements[i].checked = true;
            }
            else if(type == "checkbox"){
                var a = value.split(',');
                for(var j=0; j< a.length; j++){
                    if(a[j] == elements[i].value){
                        elements[i].checked = true;
                    }
                }
            }
        }
    };

    $.fn.formatDateYMD = function() {
        this.datebox( {
            currentText : '今天',
            closeText : '关闭',
            disabled : false,
            required : false,
            needTime:true,
            showTime:true,
            formatter : function(date){
                if (date instanceof Date) {
                    return $.getYMDStr(date);
                }
                return '';
            }
        });

        return this;
    };

    $.toJsonStr = function(object) {
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
                    var value = $.toJsonStr(object[property]);
                    if (value !== undefined) results.push($.toJsonStr(property) + ':' + value);
                }
                return '{' + results.join(',') + '}';
                break;
            case 'array':
                results = [];
                for (var i = 0; i < object.length; i++) {
                    value = $.toJsonStr(object[i]);
                    if (value !== undefined) results.push(value);
                }
                return '[' + results.join(',') + ']';
                break;
        }

        return '';
    };

    /**
     * 清除Form中文本框的内容，如果是只读属性则不清除。
     * Param: objForm Fomr对象
     */
    $.clearForm = function(formId) {
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
    };

    // 获取当前时间年月日的字符串形式 yyyy-mm-dd;
    $.getYMDStr = function(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = (m<10)?("0"+m):m;
        var d = date.getDate();
        d = (d<10)?("0"+d):d;
        return y + '-' + m + '-' + d ;
    }
})(jQuery);