/**
 * BPMN符号文本显示
 *
 * @author wei_jc
 */
package com.fly.base.drawing.bpmn {
    import flash.text.TextField;
    import flash.text.TextFieldAutoSize;

    public class NotationText extends TextField {
        public function NotationText(label:String, width:Number, x:Number = 0, y:Number = 0, textFieldAutoSize:String = TextFieldAutoSize.LEFT) {
            super();
            this.autoSize = textFieldAutoSize;
            this.x = x;
            this.y = y;
//            if (TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) { // 自动换行
                this.width = width;
            this.width = width;
            this.wordWrap = true;
            this.condenseWhite = true;
            this.multiline = true;
//            }
            this.htmlText = label;
        }
    }
}
