/**
 * Created with IntelliJ IDEA.
 * User: WangXinghao
 * Date: 12-7-23
 * Time: 上午8:03
 * To change this template use File | Settings | File Templates.
 */
function getTable() {

}

function trStart() {
    return '<tr>';
}

function trEnd() {
    return '</tr>';
}


function getGap(width) {
    return '<span style="width:' + width + '"></span>';
}

function getHGap(colspan, hGap) {
    return '<td colspan="' + colspan+ '" style="height:' + hGap + '"></td>'
}


function getLabelTd(name, labelForId, width) {
    return '<td>' + getLabel(name, labelForId, width)+ '</td>';
}

function getTextFieldTd(id, width) {
    return '<td>' + getTextField(id, width) + '</td>';
}


function getLabel(name, labelForId, width) {
    return '<label for="' + labelForId+ '" style="width:' + width + ';">' + name+ '</label>';
}

function getTextField(id, width) {
    return '<input id="' + id + '" type="text" style="width:' + width + ';"/>'
}
