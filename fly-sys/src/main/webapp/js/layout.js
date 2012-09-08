/**
 * fly系统 布局脚本文件
 *
 * User: weijiancai
 * Date: 2012-8-23
 */

function GridPane(hgap, vgap) {
    this.hgap = hgap;
    this.vgap = vgap;
    this.table = [];
}

GridPane.prototype = {
    add: function(node, row, col) {
        var array = this.table[row];
        if(!(array && array.length)) {
            array = [];
            this.table[row] = array;
        }
        array[col] = node;
    },
    toString: function() {
        var tableStr = '<table class="gridPane">';
        var array = this.table;
        for(var i = 0; i < array.length; i++) {
            var subArray = array[i];
            if(subArray) {
                tableStr += '<tr>';
                for(var j = 0; j < subArray.length; j++) {
                    tableStr += subArray[j];
                }
                tableStr += '</tr>';
            }
        }
        tableStr += '</table>';

        return tableStr;
    }
};

function getInputNode(field, colCount) {
    if(DS_TEXT_AREA == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field, 'textarea', colCount * 4 - 3);
        } else {
            return getFormInputTd(field, 'textarea');
        }
    } else if(DS_PASSWORD == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field, 'password', colCount * 4 - 3);
        } else {
            return getFormInputTd(field, 'password');
        }
    } else if(DS_COMBO_BOX == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field, 'select', colCount * 4 - 3);
        } else {
            return getFormInputTd(field, 'select');
        }
    } else {
        if(DT_DATE == field.dataType) {
            return getFormInputTd(field, 'date');
        } else if(DT_DOUBLE == field.dataType) {
            return getFormInputTd(field, "double");
        } else if(DT_INTEGER == field.dataType) {
            return getFormInputTd(field, "int");
        } else if(DT_NUMBER == field.dataType) {
            return getFormInputTd(field, "number");
        } else if(DT_URL == field.dataType) {
            return getFormInputTd(field, "url");
        } else if(DT_IP == field.dataType) {
            return getFormInputTd(field, "ip");
        } else if(DT_EMAIL == field.dataType) {
            return getFormInputTd(field, "email");
        } else {
            if(field.isSingleLine) {
                return getFormInputTd(field, 'text', colCount * 4 - 3);
            } else {
                return getFormInputTd(field, 'text');
            }
        }
    }
}

function getGap(width) {
    return '<span style="width:' + width + 'px;display:block;"></span>';
}

function getGapTd(width) {
    return '<td>' + getGap(width) + '</td>';
}

function getHGap(colspan, hGap) {
    return '<td colspan="' + colspan+ '" style="height:' + hGap + 'px"></td>'
}


function getLabelTd(field, formType) {
    return '<td>' + getLabel(field, formType)+ '</td>';
}

function getFormInputTd(field, type, colspan, rowspan) {
    var spanStr = "";
    if(colspan) {
        spanStr += ' colspan="' + colspan + '"';
        field.width = '100%';
    }
    if(rowspan) {
        spanStr += ' rowspan="' + rowspan + '"';
    }
    if(spanStr.length > 0) {
        return '<td' + spanStr + '>' + getFormInput(field, type) + '</td>';
    } else {
        return '<td>' + getFormInput(field, type) + '</td>';
    }
}


function getLabel(field, formType) {
    if(field.required && formType == "1") {
        return '<label for="' + field.name+ '" style="display:block;">' + field.displayName+ '<span class="span_required">*</span></label>';
    }

    return '<label for="' + field.name+ '">' + field.displayName+ '</label>';
}

function getFormInput(field, type) {
    var inputName;
    if(tableFieldMapping && tableFieldMapping[field.name]) {
        inputName = tableFieldMapping[field.name];
    } else {
        inputName = field.name;
    }

    var styleStr = "";
    if(field.width) {
        if('100%' == field.width) {
            styleStr += "width:" + field.width + ";";
        } else {
            if('date' == type && field.form.formType == "0") {
                styleStr += "width:" + (field.width/2 - 10) + "px;";
            } else {
                styleStr += "width:" + field.width + "px;";
            }
        }
    }
    if(field.height) {
        styleStr += "height:" + field.height + "px;";
    }
    var options = '<option value=""> </option>';
    if(field.dictList) {
        for(var i = 0; i < field.dictList.length; i++) {
            options += '<option value="' + field.dictList[i].value +'">' + field.dictList[i].name+ '</option>'
        }
    }

    var attr = '';
    var styleClass = '';
    if(field.readonly) {
        attr += ' readonly="readonly"';
    }
    if(field.required && 'hidden' != type) {
        attr += ' missingMessage="必填" invalidMessage="请输入"';
        styleClass += ' required';
    }

    if('textarea' == type) {
        return '<textarea id="' + field.id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"' + attr + ' class="' + styleClass + '"></textarea>';
    } else if('select' == type) {
        return '<select id="' + field.id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"' + attr + ' class="' + styleClass + '">' + options + '</select>';
    } else if('date' == type || 'email' == type || 'ip' == type || 'url' == type || 'int' == type || 'double' == type || 'number' == type) {
        if('date' == type) {
            styleClass += ' dateField';
            if(field.form.formType == "0") {
                return '<input id="' + field.id + '" type="text" name="D_start' + inputName + '" style="' + styleStr + '"' + attr + ' class="' + styleClass + '" queryMode="' + QM_GREATER_EQUAL + '"/>&nbsp;至&nbsp;' +
                    '<input id="' + field.id + '" type="text" name="D_end' + inputName + '" style="' + styleStr + '"' + attr + ' class="' + styleClass + '" queryMode="' + QM_LESS_THAN + '"/>';
            }
        } else if('email' == type) {
            styleClass += ' email';
        } else if('ip' == type) {
            styleClass += ' ip';
        } else if('url' == type) {
            styleClass += ' url';
        } else if('int' == type) {
            styleClass += ' int';
        } else if('double' == type) {
            styleClass += ' double';
        } else if('number' == type) {
            styleClass += ' number';
        }

        type = 'text';
    }

    var queryMode = '';

    if(field.form.formType == '0' && field.form.showQueryMode && field.isDisplay) {
        queryMode = getQueryModeLink(field.queryMode);
    }

    return '<input id="' + field.id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"' + attr + ' class="' + styleClass + '" queryMode="' + field.queryMode + '"/>' + queryMode;
}

/**
 * 获得查询模式超链接
 *
 * @param queryMode
 */
function getQueryModeLink(queryMode) {
    switch (queryMode) {
        case 0:
            return '<a href="#" class="queryMode equal">=</a>';
        case 1:
            return '<a href="#" class="queryMode equal">&lt;&gt;</a>';
        case 2:
            return '<a href="#" class="queryMode equal">&lt;</a>';
        case 3:
            return '<a href="#" class="queryMode equal">&lt;=</a>';
        case 4:
            return '<a href="#" class="queryMode equal">&gt;</a>';
        case 5:
            return '<a href="#" class="queryMode equal">&gt;=</a>';
        case 6:
            return '<a href="#" class="queryMode equal">=</a>';
        case 7:
            return '<a href="#" class="queryMode equal">%%</a>';
        case 8:
            return '<a href="#" class="queryMode equal">*%</a>';
        case 9:
            return '<a href="#" class="queryMode equal">%*</a>';
        default:
            return '<a href="#" class="queryMode equal">=</a>';
    }
}

