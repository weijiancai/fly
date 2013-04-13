/**
 *
 * @author weijiancai
 */
package com.fly.designer.view {
    import com.fly.mvc.view.ViewHelper;

    import flash.events.Event;

    import mx.containers.Panel;
    import mx.controls.TextArea;

    public class BPMNEditorVH extends ViewHelper {
        private var _textArea :TextArea = new TextArea;

        public function BPMNEditorVH(document : Object, id : String) {
            super();

            initialized(document, id);
        }
    }
}
