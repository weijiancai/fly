/**
 * 数据窗体
 */
function DataWindow(classDefine) {
    classDefine['queryForm'].fieldset = {};
    var actionBar = new ActionBar();
    actionBar.add(new ActionButton("", "", "查询", "formQuery"));
    actionBar.add(new ActionButton("", "", "重置", "formReset"));
    classDefine['queryForm'].actionBar = actionBar;
    this.clazz = new DataClass(classDefine);
}

DataWindow.prototype = {
    toString: function() {
        return '<div class="easyui-layout">' + getNorth(this.clazz) + '</div>';
    }
};

function getNorth(clazz) {
    return '<div region="north" split="false" style="height: 100px;" border="false">' + clazz.queryForm.toString() + '</div>'
}

function formQuery() {
    alert("查询");
}

function formReset() {
    alert("重置");
}