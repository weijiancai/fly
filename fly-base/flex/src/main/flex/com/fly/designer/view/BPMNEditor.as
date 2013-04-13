/**
 *
 * @author weijiancai
 */
package com.fly.designer.view {
    import flash.events.Event;

    import mx.containers.Panel;
    import mx.controls.TextArea;

    public class BPMNEditor extends Panel {
        private var _textArea :TextArea = new TextArea;
        private var bpelEditorVH :BPMNEditorVH;

        public function BPMNEditor() {
            super();

            this.percentHeight = 100;
            this.setStyle("headerHeight","5");
            this.percentWidth = 100;
            _textArea.percentHeight=100;
            _textArea.percentWidth=100;
//            _textArea.addEventListener(Event.CHANGE, changeHandler);
            this.addChild(_textArea);

            //view helper
            this.bpelEditorVH = new BPMNEditorVH(this, "BPMNEditorVH");
        }
    }
}
