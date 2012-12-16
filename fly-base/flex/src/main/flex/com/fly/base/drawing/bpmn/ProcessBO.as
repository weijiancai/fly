/**
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotaionEvent;

    import mx.containers.Canvas;

    public class ProcessBO {
        private var canvas:Canvas;
        private var processDefinitionXml:XML;
        private var processIconList:Array = [];
        private var currentSelected:BPMNotaion = null;

        public function ProcessBO(canvas:Canvas, processDefinitionXml:XML) {
            this.processDefinitionXml = processDefinitionXml;
        }

        private function init():void {
            clearCanvas();
            //填充背景
            canvas.graphics.beginFill(0xFFFFFF);
            canvas.graphics.drawRect(0, 0, canvas.width, canvas.height);
            canvas.graphics.endFill();
        }

        public function draw():void {
            clearCanvas();

            var model:Namespace = processDefinitionXml.namespace();

            var bpmndi:Namespace = getNameSpace(processDefinitionXml, "http://www.omg.org/spec/BPMN/20100524/DI");

            var processNodeList:XMLList = processDefinitionXml.model::process.*;
            var node:XML;
            var processIcon:BPMNotaion;

            for (var i:int = 0; i < processNodeList.length(); i++) {
                node = processNodeList[i];

                var diNode:XML;
                if(bpmndi != null) {
                    trace(node.@id.toString());

                    var list:XMLList = processDefinitionXml.bpmndi::BPMNDiagram..bpmndi::BPMNShape.(@bpmnElement == node.@id.toString());
                    if(list.length() > 0) {
                        diNode = list[0];
                    } else {
                        list = processDefinitionXml.bpmndi::BPMNDiagram..bpmndi::BPMNEdge.(@bpmnElement == node.@id.toString());
                        if(list.length() > 0) {
                            diNode = list[0];
                        }
                    }
                }

                processIcon = new BPMNotaion(node,  diNode);
                processIconList.push(processIcon);
                canvas.addChild(processIcon);
                processIcon.addEventListener(NotaionEvent.ICON_MOUSE_DOWN, onIconMouseDownHandler);
            }
        }

        // 清空画布
        public function clearCanvas():void {
            canvas.removeAllChildren();
        }

        internal function onIconMouseDownHandler(event:NotaionEvent):void {
            clearAllSelected();
            trace(event.type);
            currentSelected = event.icon;
            currentSelected.selected();
        }

        /**
         * 获取指定uri的命名空间
         *
         * @param xml
         * @param uriStr
         * @return
         */
        private static function getNameSpace(xml:XML, uriStr:String):Namespace {
            for(var i:uint = 0; i < xml.namespaceDeclarations().length; i++) {
                var ns:Namespace = xml.namespaceDeclarations()[i];
                var uri:String = ns.uri;
                if(uri == uriStr) {
                    return ns;
                }
            }

            return null;
        }

        public function highlightNode(key:String):void {
            var processIcon:ProcessIcon;
            for(var i:int = 0; i < processIconList.length; i++) {
                processIcon = processIconList[i];
                if(processIcon.key == key) {
                    processIcon.highlight();
                }
            }
        }

        // 清除所有选中
        private function clearAllSelected():void {
            for(var i:int = 0; i < processIconList.length; i++) {
                var processIcon:ProcessIcon = processIconList[i];
                processIcon.clearSelected();
            }
        }

        // 清除所有高亮
        private function clearAllHighlight():void {
            for(var i:int = 0; i < processIconList.length; i++) {
                var processIcon:ProcessIcon = processIconList[i];
                processIcon.clearHighlight();
            }
        }

        // 获取当前选中的节点
        public function getCurrentSelected():ProcessIcon {
            return currentSelected;
        }
    }
}
