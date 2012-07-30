/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-5-9
 * Time: 下午10:51
 * To change this template use File | Settings | File Templates.
 */
$(function() {
    // 获取项目信息
    $.post("projectDefine.class", function(data) {
        alert(data);
    })
});

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
            formStr += '<fieldset><legend>' + legendStr + '</legend>' + this.gridPane.toString() +'</fieldset>'
        } else {
            formStr += this.gridPane.toString();
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