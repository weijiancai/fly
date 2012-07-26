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

function genTable(classForm) {
    if(!classForm) {
        return '';
    }
    var form = new DataForm(classForm);
    var formGrid = new GridPane(form.hgap, form.vgap);

    var idxRow = 0;
    var idxCol = 0;
    var fieldList = form.fieldList;
    var field;
    for(var i = 0; i < fieldList.length; i++) {
        field = fieldList[i];
        if(!field.isDisplay) { // 不显示
            continue;
        }

        if(field.isSingleLine) {
            idxRow++;
            formGrid.add(getLabelTd(field.displayName), idxRow, 0);
            formGrid.add(getGapTd(field.labelGap), idxRow, 1);
            formGrid.add(getTextFieldTd(field.id, field.width, field.height, form.colCount * 4 - 5), idxRow, 2);
            idxCol = 0;
            idxRow++;

            continue;
        }

        formGrid.add(getLabelTd(field.displayName), idxRow, idxCol++);
        formGrid.add(getGapTd(field.labelGap), idxRow, idxCol++);
        formGrid.add(getTextFieldTd(field.id, field.width, field.height), idxRow, idxCol++);

        if(form.colCount == 1) {
            idxCol = 0;
            idxRow++;
        } else {
            if(idxCol == form.colCount * 4 - 1) {
                idxCol = 0;
                idxRow++;
            } else {
                formGrid.add(getGapTd(field.fieldGap), idxRow, idxCol++);
            }
        }
    }

    return formGrid.toString();
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
        var tableStr = '<table>';
        var array = this.table;
        for(var i = 0; i < array.length; i++) {
            tableStr += '<tr>';
            var subArray = array[i];
            for(var j = 0; j < subArray.length; j++) {
                tableStr += subArray[j];
            }
            tableStr += '</tr>';
        }
        tableStr += '</table>';

        return tableStr;
    }
};

function DataForm(classForm) {
    this.id = classForm['id'];
    this.name = classForm['name'];
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

    for(var i = 0; i < classForm['fieldList'].length; i++) {
        this.fieldList.push(new FormField(classForm['fieldList'][i]));
    }
}

function FormField(fd) {
    this.id = fd['id'];
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
    return '<span style="width:' + width + '"></span>';
}

function getGapTd(width) {
    return '<td>' + getGap(width) + '"</td>';
}

function getHGap(colspan, hGap) {
    return '<td colspan="' + colspan+ '" style="height:' + hGap + '"></td>'
}


function getLabelTd(name, labelForId, width) {
    return '<td>' + getLabel(name, labelForId, width)+ '</td>';
}

function getTextFieldTd(id, width, height, colspan, rowspan) {
    var spanStr = "";
    if(colspan) {
        spanStr += ' colspan="' + colspan + '"';
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
    return '<label for="' + labelForId+ '" style="width:' + width + ';">' + name+ '</label>';
}

function getTextField(id, width, height) {
    var styleStr = "";
    if(width) {
        styleStr += "width:" + width + ";";
    }
    if(height) {
        styleStr += "height:" + height + ";";
    }
    if(styleStr.length > 0) {
        return '<input id="' + id + '" type="text" style="' + styleStr + '"/>'
    } else {
        return '<input id="' + id + '" type="text"/>'
    }

}