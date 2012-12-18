/**
 * BPMN2.0文件
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import flash.events.Event;
    import flash.net.FileFilter;
    import flash.net.FileReference;

    public class BPMNFile {
        private var xmlFile:FileReference;
        private var callback:Function;

        public function BPMNFile(callback:Function) {
            this.callback = callback;
            xmlFile = new FileReference();
            xmlFile.addEventListener(Event.SELECT, selectFileHandler);
            xmlFile.addEventListener(Event.COMPLETE, completeHandler);
        }

        private static function selectFileHandler(event:Event):void {
            var file:FileReference = FileReference(event.target);
            file.load();
        }

        private function completeHandler(event:Event):void {
            var xml:XML = XML(event.target.data);
            if(null != callback) {
                callback(xml);
            }

        }

        public function open():void {
            xmlFile.browse(getTypeFilter());
        }

        private static function getTypeFilter():Array {
            var imagesFilter:FileFilter = new FileFilter("BPMN 2.0 (*.xml,*.bpmn)", "*.xml;*.bpmn");
            return [imagesFilter];
        }
    }
}
