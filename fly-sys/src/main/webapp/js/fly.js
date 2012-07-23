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
    var form = new DataForm(classForm);
    var tableStr = "<table>";

    var idxRow = 0;
    var idxCol = 0;
    var fieldList = form.fieldList;
    var field;
    for(var i = 0; i < fieldList.length; i++) {
        field = fieldList[i];
        if("true" !== field.isDisplay) { // 不显示
            continue;
        }

        if("true" == field.isSingleLine) {
            idxRow++;
            tableStr += "<tr></tr>";
        }
    }
}

function GridPane() {
    this.table = [];
}

GridPane.prototype.add = function(node, row, col) {
    var array = this.table[row];
    if(!(array && array.length)) {
        this.table[row] = [];
    }
    array[col] = node;
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
    this.isValid = classForm['isValid'];
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
    this.isSingleLine = fd['isSingleLine'];
    this.isDisplay = fd['isDisplay'];
    this.width = fd['width'];
    this.height = fd['height'];
    this.displayStyle = fd['displayStyle'];
    this.inputDate = fd['inputDate'];
    this.isValid = fd['isValid'];
    this.sortNum = fd['sortNum'];
}