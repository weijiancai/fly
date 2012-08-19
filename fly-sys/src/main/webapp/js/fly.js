function DataClass(classDefine) {
    this.id = classDefine['id'];
    this.name = classDefine['name'];
    this.cname = classDefine['cname'];
    this.superClass = classDefine['superClass'];
    this.author = classDefine['author'];
    this.desc = classDefine['desc'];
    this.version = classDefine['version'];
    this.isValid = classDefine['isValid'];
    this.sortNum = classDefine['sortNum'];
    this.dictMap = classDefine['dictMap'];

    this.queryForm = new DataForm(classDefine['queryForm'], this.dictMap);
    this.editForm = new DataForm(classDefine['editForm'], this.dictMap);
    this.dataTable = new DataTable(classDefine['classTable']);
}

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

function DataForm(classForm, dictMap) {
    this.id = classForm['id'];
    this.name = classForm['name'];
    this.formType = classForm['formType'];
    this.colCount = classForm['colCount'];
    this.colWidth = classForm['colWidth'];
    this.labelGap = classForm['labelGap'];
    this.fieldGap = classForm['fieldGap'];
    this.hgap = classForm['hgap'];
    this.vgap = classForm['vgap'];
    this.isValid = classForm['valid'];
    this.inputDate = classForm['inputDate'];
    this.sortNum = classForm['sortNum'];
    this.fieldList = [];
    this.gridPane = new GridPane(this.hgap, this.vgap);
    this.fieldset = classForm['fieldset'];
    this.actionBar = classForm['actionBar'];
    this.width = 90 * this.colCount + this.colWidth * this.colCount + this.labelGap * this.colCount + (this.fieldGap - 1) * this.colCount;
    this.height = 0;

    for(var i = 0; i < classForm['fieldList'].length; i++) {
        var formField = classForm['fieldList'][i];
        this.fieldList.push(new FormField(formField, dictMap));
    }

    // 按SortNum排序
    this.fieldList.sort(function(a, b) {
        return a.sortNum - b.sortNum;
    });
    // 初始化
    this.init();
}

DataForm.prototype = {
    init: function() {
        this.height = 100;
        var formGrid = this.gridPane;

        var idxRow = 0;
        var idxCol = 0;
        var fieldList = this.fieldList;
        var field;
        for(var i = 0; i < fieldList.length; i++) {
            field = fieldList[i];
            if(!field.isDisplay) { // 不显示
                continue;
            }

            if(field.isSingleLine) {
                idxRow++;
                formGrid.add(getLabelTd(field.displayName, field.name), idxRow, 0);
                formGrid.add(getGapTd(this.labelGap), idxRow, 1);
                formGrid.add(getInputNode(field, this.colCount), idxRow, 2);
                idxCol = 0;
                idxRow++;
                this.height += field.height;

                continue;
            }

            formGrid.add(getLabelTd(field.displayName, field.name), idxRow, idxCol++);
            formGrid.add(getGapTd(this.labelGap), idxRow, idxCol++);
            formGrid.add(getInputNode(field, this.colCount), idxRow, idxCol++);

            if(this.colCount == 1) {
                idxCol = 0;
                idxRow++;
            } else {
                if(idxCol == this.colCount * 4 - 1) {
                    idxCol = 0;
                    idxRow++;
                } else {
                    formGrid.add(getGapTd(this.fieldGap), idxRow, idxCol++);
                }
            }
        }

        this.height += (idxRow + 1) * 25;
    },
    toString: function() {
        var styleClass = '';
        var legendStr = '';
        if("0" == this.formType) {
            styleClass = 'queryForm';
            legendStr = '查询条件';
        } else if("1" == this.formType) {
            styleClass = 'editForm';
        }
        var formStr = '<form id="' + this.id + '" class="' + styleClass + '">';
        if(this.fieldset) {
            formStr += '<fieldset><legend>' + legendStr + '</legend>';
        }

        formStr += this.gridPane.toString();

        formStr += '<div class="actionBar" style="text-align: center;">';
        if(this.actionBar) {
            formStr += this.actionBar.toString();
        }
        formStr += '</div>';

        if(this.fieldset) {
            formStr += '</fieldset>';
        }
        return formStr + '</form>';
    }
};

function FormField(fd, dictMap) {
    this.id = fd['id'];
    this.name = fd['name'];
    this.displayName = fd['displayName'];
    this.isSingleLine = fd['singleLine'];
    this.isDisplay = fd['display'];
    this.width = fd['width'];
    this.height = fd['height'];
    this.displayStyle = fd['displayStyle'];
    this.inputDate = fd['inputDate'];
    this.isValid = fd['valid'];
    this.sortNum = fd['sortNum'];
    this.dictList = dictMap[this.name];
}
var DS_TEXT = 0;
var DS_TEXT_AREA = 1;
var DS_PASSWORD = 2;
var DS_COMBO_BOX = 3;

function getInputNode(field, colCount) {
    if(DS_TEXT == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field, 'text', colCount * 4 - 3);
        } else {
            return getFormInputTd(field, 'text');
        }
    } else if(DS_TEXT_AREA == field.displayStyle) {
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
    }

    return "";
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


function getLabelTd(name, labelForId, width) {
    return '<td>' + getLabel(name, labelForId, width)+ '</td>';
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


function getLabel(name, labelForId, width) {
    if(width) {
        return '<label for="' + labelForId+ '" style="width:' + width + 'px;display:block;">' + name+ '</label>';
    }

    return '<label for="' + labelForId+ '">' + name+ '</label>';
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
            styleStr += "width:" + field.width + "px;";
        }
    }
    if(field.height) {
        styleStr += "height:" + field.height + "px;";
    }
    var options = '';
    if(field.dictList) {
        for(var i = 0; i < field.dictList.length; i++) {
           options += '<option value="' + field.dictList[i].value +'">' + field.dictList[i].name+ '</option>'
        }
    }

    if(styleStr.length > 0) {
        if('textarea' == type) {
            return '<textarea id="' + field.name + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"></textarea>';
        } else if('select' == type) {
            return '<select id="' + field.name + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '">' + options + '</select>';
        } else {
            return '<input id="' + field.name + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"/>';
        }
    } else {
        if('textarea' == type) {
            return '<textarea id="' + field.name + '" type="' + type + '" name="' + inputName + '"></textarea>';
        } else if('select' == type) {
            return '<select id="' + field.name + '" type="' + type + '" name="' + inputName + '">' + options + '</select>';
        }  else {
            return '<input id="' + field.name + '" type="' + type + '" name="' + inputName + '"/>'
        }
    }
}

/**
 * 控制条控件
 */
function ActionBar() {
    this.actions = [];
}

ActionBar.prototype = {
    add: function(actionButton) {
        this.actions.push(actionButton);
    },
    toString: function() {
        var str = '';
        for(var i = 0; i < this.actions.length; i++) {
            str += this.actions[i].toString();
        }
        return str;
    }
};

function ActionButton(id, name, value, onClick) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.onClick = onClick;
}

ActionButton.prototype = {
    toString: function() {
//        return '<input type="button" id="' + this.id + '" name="' + this.name + '" value="' + this.value + '" onclick="'+ this.onClick +'()"/>';
        return '<a href="#"  id="' + this.id + '" onclick="'+ this.onClick +'()">' + this.value + '</a>';
    }
};

function DataTable(classTable) {
    this.id = classTable['id'];
    this.name = classTable['name'];
    this.colWidth = classTable['colWidth'];
    this.isValid = classTable['isValid'];
    this.tableBar = classTable['tableBar'];
    this.fieldList = [];

    var tableFieldList = classTable['tableFieldList'];
    for(var i = 0; i < tableFieldList.length; i++) {
        this.fieldList.push(new TableField(tableFieldList[i]));
    }

    // 按SortNum排序
    this.fieldList.sort(function(a, b) {
        return a.sortNum - b.sortNum;
    });
}

DataTable.prototype = {
    toString: function() {
        var str = '<table id="' + this.id + '" class="easyui-datagrid"><thead><tr>';

        var fieldName;
        for(var i = 0; i < this.fieldList.length; i++) {
            var field = this.fieldList[i];
            if(field) {
                if(tableFieldMapping && tableFieldMapping[field.name]) {
                    fieldName = tableFieldMapping[field.name];
                } else {
                    fieldName = field.name;
                }
                str += '<th field="' + fieldName + '" width="' + field.colWidth + '" align="' + field.align + '">' + field.displayName + '</th>'
            }
        }

        return str +'</tr></thead></table>';
    }
};

function TableField(classTableField) {
    this.id = classTableField['id'];
    this.name = classTableField['name'];
    this.align = classTableField['align'];
    this.displayName = classTableField['displayName'];
    this.displayStyle = classTableField['displayStyle'];
    this.isDisplay = classTableField['isDisplay'];
    this.colWidth = classTableField['colWidth'];
    this.isValid = classTableField['isValid'];
    this.sortNum = classTableField['sortNum'];
}