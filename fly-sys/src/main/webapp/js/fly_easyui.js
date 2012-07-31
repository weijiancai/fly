/**
 * 数据窗体
 */
function DataWindow(classDefine) {
    this.clazz = new DataClass(classDefine);
}

DataWindow.prototype = {
    toString: function() {
        return '<div class="easyui-layout">' + getNorth(this.clazz) + '</div>'
    }
};

function getNorth(clazz) {
    return '<div region="north" split="false" class="datagrid-body" border="false">' + clazz.queryForm.toString() + '</div>'
}