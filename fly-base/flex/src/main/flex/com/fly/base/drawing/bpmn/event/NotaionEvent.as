/**
 * 流程符号事件
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn.event {
    import com.fly.base.drawing.bpmn.BPMNotaion;

    import flash.events.Event;

    public class NotaionEvent extends Event {
        public static var ICON_MOUSE_DOWN:String = "icon_mouse_down";
        public static var ICON_MOUSE_UP:String = "icon_mouse_up";
        public static var ICON_MOVE:String = "icon_move";

        public var icon:BPMNotaion;

        public function NotaionEvent(type:String) {
            super(type);
        }
    }
}
