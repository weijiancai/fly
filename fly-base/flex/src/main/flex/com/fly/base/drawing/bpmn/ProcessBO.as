/**
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotationEvent;

    import mx.containers.Canvas;
    import mx.controls.Alert;

    public class ProcessBO {
        private var canvas:Canvas;
        private var processDefinitionXml:XML;
        private var processNotationList:Array = [];
        private var currentSelected:BPMNotation = null;
        private var bpmndi:Namespace;
        private var processNodeList:XMLList;

        public function ProcessBO(canvas:Canvas, processDefinitionXml:XML) {
            this.canvas = canvas;
            this.processDefinitionXml = processDefinitionXml;

            var model:Namespace = processDefinitionXml.namespace();
            bpmndi = getNameSpace(processDefinitionXml, "http://www.omg.org/spec/BPMN/20100524/DI");
            processNodeList = processDefinitionXml.model::process.*;
        }

        public function draw():void {
            clearCanvas();

            if(bpmndi == null) {
                Alert.show("获取BPMN DI数据失败！");
                return;
            }

            var diNodeArray:Array = [];
            var node:XML;
            var notation:BPMNotation;
            var y_min:Number = 1000; // y坐标的最小值，用于计算图形相对于最上边的最小距离


            for (var i:int = 0; i < processNodeList.length(); i++) {
                node = processNodeList[i];

                if("laneSet" == node.localName()) {
                    for(var j:int = 0; j < node.children().length(); j++) {
                        processDI(node.children()[j]);
                    }
                } else {
                    processDI(node);
                }
            }

            function processDI(node:XML):void {
                var diNode:XML;
                var list:XMLList = processDefinitionXml.bpmndi::BPMNDiagram..bpmndi::BPMNShape.(@bpmnElement == node.@id.toString());
                if(list.length() > 0) {
                    diNode = list[0];
                    var y:Number = Number(diNode.children()[0].@y);
                    y_min = y < y_min ? y : y_min;
                } else {
                    list = processDefinitionXml.bpmndi::BPMNDiagram..bpmndi::BPMNEdge.(@bpmnElement == node.@id.toString());
                    if(list.length() > 0) {
                        diNode = list[0];
                    }
                }
                diNodeArray.push({node : node, di : diNode});
            }

            for(i = 0; i < diNodeArray.length; i++) {
                node = diNodeArray[i].node;
                var diNode:XML = diNodeArray[i].di;
                notation = new BPMNotation(node,  diNode, 15 - y_min); // 距离最上边15
                processNotationList.push(notation);
                canvas.addChild(notation);
                notation.addEventListener(NotationEvent.ICON_MOUSE_DOWN, onIconMouseDownHandler);
            }
        }

        // 清空画布
        public function clearCanvas():void {
            canvas.removeAllChildren();
        }

        internal function onIconMouseDownHandler(event:NotationEvent):void {
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
            var processIcon:BPMNotation;
            for(var i:int = 0; i < processNotationList.length; i++) {
                processIcon = processNotationList[i];
                if(processIcon.key == key) {
                    processIcon.highlight();
                }
            }
        }

        // 清除所有选中
        private function clearAllSelected():void {
            for(var i:int = 0; i < processNotationList.length; i++) {
                var processIcon:BPMNotation = processNotationList[i];
                processIcon.clearSelected();
            }
        }

        // 清除所有高亮
        private function clearAllHighlight():void {
            for(var i:int = 0; i < processNotationList.length; i++) {
                var processIcon:BPMNotation = processNotationList[i];
                processIcon.clearHighlight();
            }
        }

        // 获取当前选中的节点
        public function getCurrentSelected():BPMNotation {
            return currentSelected;
        }
    }
}
