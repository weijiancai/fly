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

    this.queryForm = new DataForm(classDefine['queryForm']);
    this.editForm = new DataForm(classDefine['editForm']);
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

function DataForm(classForm) {
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

    for(var i = 0; i < classForm['fieldList'].length; i++) {
        this.fieldList.push(new FormField(classForm['fieldList'][i]));
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

        if(this.actionBar) {
            formStr += this.actionBar.toString();
        }

        if(this.fieldset) {
            formStr += '</fieldset>';
        }
        return formStr + '</form>';
    }
};

function FormField(fd) {
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
}
var DS_TEXT = 0;
var DS_TEXT_AREA = 1;
var DS_PASSWORD = 2;
var DS_COMBO_BOX = 3;

function getInputNode(field, colCount) {
    if(DS_TEXT == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field.name, 'text', field.width, field.height, colCount * 4 - 3);
        } else {
            return getFormInputTd(field.name, 'text', field.width, field.height);
        }
    } else if(DS_TEXT_AREA == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field.name, 'textarea', field.width, field.height, colCount * 4 - 3);
        } else {
            return getFormInputTd(field.name, 'textarea', field.width, field.height);
        }
    } else if(DS_PASSWORD == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field.name, 'password', field.width, field.height, colCount * 4 - 3);
        } else {
            return getFormInputTd(field.name, 'password', field.width, field.height);
        }
    } else if(DS_COMBO_BOX == field.displayStyle) {
        if(field.isSingleLine) {
            return getFormInputTd(field.name, 'select', field.width, field.height, colCount * 4 - 3);
        } else {
            return getFormInputTd(field.name, 'select', field.width, field.height);
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

function getFormInputTd(id, type, width, height, colspan, rowspan) {
    var spanStr = "";
    if(colspan) {
        spanStr += ' colspan="' + colspan + '"';
        width = '100%';
    }
    if(rowspan) {
        spanStr += ' rowspan="' + rowspan + '"';
    }
    if(spanStr.length > 0) {
        return '<td' + spanStr + '>' + getFormInput(id, id, type, width, height) + '</td>';
    } else {
        return '<td>' + getFormInput(id, id, type, width, height) + '</td>';
    }
}


function getLabel(name, labelForId, width) {
    if(width) {
        return '<label for="' + labelForId+ '" style="width:' + width + 'px;display:block;">' + name+ '</label>';
    }

    return '<label for="' + labelForId+ '">' + name+ '</label>';
}

function getFormInput(id, name, type, width, height) {
    var inputName;
    if(tableFieldMapping && tableFieldMapping[name]) {
        inputName = tableFieldMapping[name];
    } else {
        inputName = name;
    }

    var styleStr = "";
    if(width) {
        if('100%' == width) {
            styleStr += "width:" + width + ";";
        } else {
            styleStr += "width:" + width + "px;";
        }
    }
    if(height) {
        styleStr += "height:" + height + "px;";
    }
    if(styleStr.length > 0) {
        if('textarea' == type) {
            return '<textarea id="' + id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"></textarea>';
        } else if('select' == type) {
            return '<select id="' + id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"></select>';
        } else {
            return '<input id="' + id + '" type="' + type + '" name="' + inputName + '" style="' + styleStr + '"/>';
        }
    } else {
        if('textarea' == type) {
            return '<textarea id="' + id + '" type="' + type + '" name="' + inputName + '"></textarea>';
        } else if('select' == type) {
            return '<select id="' + id + '" type="' + type + '" name="' + inputName + '"></select>';
        }  else {
            return '<input id="' + id + '" type="' + type + '" name="' + inputName + '"/>'
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
        var str = '<div class="actionBar" style="text-align: center;">';
        for(var i = 0; i < this.actions.length; i++) {
            str += this.actions[i].toString();
        }
        str += '</div>';
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