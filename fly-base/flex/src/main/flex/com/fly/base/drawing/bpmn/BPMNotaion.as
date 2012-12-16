/**
 * 业务流程模型符号
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotaionEvent;

    import flash.text.TextField;

    import mx.containers.Canvas;
    import mx.effects.Glow;

    [Event(name = NotaionEvent.ICON_MOUSE_DOWN, type = "com.fly.base.drawing.bpmn.event.NotaionEvent")]
    [Event(name = NotaionEvent.ICON_MOUSE_UP, type = "com.fly.base.drawing.bpmn.event.NotaionEvent")]
    [Event(name = NotaionEvent.ICON_MOVE, type = "com.fly.base.drawing.bpmn.event.NotaionEvent")]

    public class BPMNotaion extends Canvas {
        public static const TYPE_START_EVENT:String = "startEvent";
        public static const TYPE_END_EVENT:String = "endEvent";
        public static const TYPE_USER_TASK:String = "userTask";
        public static const TYPE_SERVICE_TASK:String = "serviceTask";
        public static const TYPE_EXCLUSIVE_GATEWAY:String = "exclusiveGateway";
        public static const TYPE_PARALLEL_GATEWAY:String = "parallelGateway";
        public static const TYPE_SEQUENCE_FLOW:String = "sequenceFlow";

        // 名称
        private var _key:String;
        // 图标类型
        private var _type:String;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        public var isSelected:Boolean = false;
        // 图标是否高亮
        public var isHighlight:Boolean = false;
        // 图像数据
        private var _icon:Object;

        // X坐标
        private var _x:Number;
        // Y坐标
        private var _y:Number;
        // 宽度
        private var _width:Number;
        // 高度
        private var _height:Number;
        //箭头的大小
        public var radius:int = 5;

        private var text:TextField = new TextField();

        private var glow:Glow = new Glow();
        private var _di:XML;

        private var selectedRectBorder:IconBorder;
        private var highlightRectBorder:IconBorder;


        public function BPMNotaion(node:XML, di:XML) {

        }
    }
}
