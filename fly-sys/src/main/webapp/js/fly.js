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
                formGrid.add(getTextFieldTd(field.name, field.width, field.height, this.colCount * 4 - 3), idxRow, 2);
                idxCol = 0;
                idxRow++;

                continue;
            }

            formGrid.add(getLabelTd(field.displayName, field.name), idxRow, idxCol++);
            formGrid.add(getGapTd(this.labelGap), idxRow, idxCol++);
            formGrid.add(getTextFieldTd(field.name, field.width, field.height), idxRow, idxCol++);

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
        var formStr = '<form id="' + this.name + '" class="' + styleClass + '">';
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

function getTextFieldTd(id, width, height, colspan, rowspan) {
    var spanStr = "";
    if(colspan) {
        spanStr += ' colspan="' + colspan + '"';
        width = '100%';
    }
    if(rowspan) {
        spanStr += ' rowspan="' + rowspan + '"';
    }
    if(spanStr.length > 0) {
        return '<td' + spanStr + '>' + getTextField(id, width, height) + '</td>';
    } else {
        return '<td>' + getTextField(id, width, height) + '</td>';
    }
}


function getLabel(name, labelForId, width) {
    if(width) {
        return '<label for="' + labelForId+ '" style="width:' + width + 'px;display:block;">' + name+ '</label>';
    }

    return '<label for="' + labelForId+ '">' + name+ '</label>';
}

function getTextField(id, width, height) {
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
        return '<input id="' + id + '" type="text" style="' + styleStr + '"/>'
    } else {
        return '<input id="' + id + '" type="text"/>'
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
        var str = '<div style="text-align: center;">';
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
        return '<input type="button" id="' + this.id + '" name="' + this.name + '" value="' + this.value + '" onclick="'+ this.onClick +'()"/>';
    }
};