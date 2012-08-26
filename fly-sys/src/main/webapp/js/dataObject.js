/**
 * fly系统 数据对象定义脚本文件
 *
 * User: weijiancai
 * Date: 2012-8-23 12:05
 */

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
    this.colNameMap = {};

    this.queryForm = new DataForm(classDefine['queryForm'], this.dictMap);
    this.editForm = new DataForm(classDefine['editForm'], this.dictMap);
    this.dataTable = new DataTable(classDefine['classTable']);

    var editFormFieldList = classDefine['editForm']['fieldList'];
    for(var i = 0; i < editFormFieldList.length; i++) {
        var formField = editFormFieldList[i];
        this.colNameMap[formField['name']] = formField['colName'];
    }
}

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
    this.width = 100 * this.colCount + this.colWidth * this.colCount + this.labelGap * this.colCount + (this.fieldGap - 1) * this.colCount;
    this.height = 0;
    this.fieldMap = {};
    this.hiddenList = []; // 隐藏域列表

    for(var i = 0; i < classForm['fieldList'].length; i++) {
        var formField = classForm['fieldList'][i];
        this.fieldList.push(new FormField(formField, dictMap, this));
        this.fieldMap[formField['name']] = formField;
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
        if("0" == this.formType) {
            this.height = 60;
        } else {
            this.height = 100;
        }
        var formGrid = this.gridPane;

        var idxRow = 0;
        var idxCol = 0;
        var fieldList = this.fieldList;
        var field;
        for(var i = 0; i < fieldList.length; i++) {
            field = fieldList[i];
            if(!field.isDisplay) { // 不显示
                this.hiddenList.push(field);
                continue;
            }

            if(field.isSingleLine) {
                idxRow++;
                formGrid.add(getLabelTd(field, this.formType), idxRow, 0);
                formGrid.add(getGapTd(this.labelGap), idxRow, 1);
                formGrid.add(getInputNode(field, this.colCount), idxRow, 2);
                idxCol = 0;
                idxRow++;
                this.height += field.height;

                continue;
            }

            formGrid.add(getLabelTd(field, this.formType), idxRow, idxCol++);
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

        // 添加隐藏域
        for(var i = 0; i < this.hiddenList.length; i++) {
            formStr += '<input type="hidden" name="' + this.hiddenList[i].name + '"/>';
        }

        return formStr + '</form>';
    },
    getValue: function(name, value) {
        if(this.fieldMap[name]) {
            if(DT_DATE == this.fieldMap[name]['dataType']) {
                return value.substring(0, 10);
            }
        }

        return value;
    }
};

function FormField(fd, dictMap, form) {
    this.id = fd['id'];
    this.name = fd['name'];
    this.colName = fd['colName'];
    this.displayName = fd['displayName'];
    this.isSingleLine = fd['singleLine'];
    this.isDisplay = fd['display'];
    this.width = fd['width'];
    this.height = fd['height'];
    this.displayStyle = fd['displayStyle'];
    this.inputDate = fd['inputDate'];
    this.isValid = fd['valid'];
    this.sortNum = fd['sortNum'];
    this.dataType = fd['dataType'];
    this.dictList = dictMap[this.name];
    this.queryMode = fd['queryMode'];
    this.readonly = fd['readonly'];
    this.required = fd['required'];
    this.form = form;
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

function ActionButton(id, value) {
    this.id = id;
    this.value = value;
}

ActionButton.prototype = {
    toString: function() {
        return '<a href="#"  id="' + this.id + '">' + this.value + '</a>';
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

function QueryCondition() {
    this.conditions = [];
    this.addCondition = function(name, queryMode, value) {
        this.conditions.push({name: name, queryMode: queryMode, value: value});
    };
    this.toString = function() {
        return {
            method: 'query',
            conditions: $.toJsonStr(this.conditions)
        };
    };
}
