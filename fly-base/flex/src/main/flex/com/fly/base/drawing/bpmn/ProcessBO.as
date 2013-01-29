/**
 *
 * @author weijiancai
 */
package com.fly.base.drawing.bpmn {
    import com.fly.base.drawing.bpmn.event.NotationEvent;
    import com.fly.base.drawing.bpmn.notation.BaseNotation;
    import com.fly.base.drawing.bpmn.notation.EndEventNotation;
    import com.fly.base.drawing.bpmn.notation.ExclusiveNotation;
    import com.fly.base.drawing.bpmn.notation.LaneNotation;
    import com.fly.base.drawing.bpmn.notation.ParallelNotation;
    import com.fly.base.drawing.bpmn.notation.PoolNotation;
    import com.fly.base.drawing.bpmn.notation.SequenceFlowNotation;
    import com.fly.base.drawing.bpmn.notation.ServiceTaskNotation;
    import com.fly.base.drawing.bpmn.notation.StartEventNotation;
    import com.fly.base.drawing.bpmn.notation.SubProcessNotation;
    import com.fly.base.drawing.bpmn.notation.UserTaskNotation;
    import com.fly.base.drawing.bpmn.util.NotationUtil;

    import mx.containers.Canvas;
    import mx.controls.Alert;

    public class ProcessBO {
        private var canvas:Canvas;
        private var processDefinitionXml:XML;
        private var processNotationList:Array = [];
        private var currentSelected:BPMNotation = null;
        private var model:Namespace;
        private var bpmndi:Namespace;
        private var processNodeList:XMLList;
        public var editable:Boolean = false; // 默认不可编辑

        private var y_min:Number = 1000; // y坐标的最小值，用于计算图形相对于最上边的最小距离
        private var diNodeArray:Array = [];

        public function ProcessBO(canvas:Canvas, processDefinitionXml:XML) {
            this.canvas = canvas;
            this.processDefinitionXml = processDefinitionXml;

            model = processDefinitionXml.namespace();
            bpmndi = getNameSpace(processDefinitionXml, "http://www.omg.org/spec/BPMN/20100524/DI");
            processNodeList = processDefinitionXml.model::process.*;
        }

        public function draw():void {
            clearCanvas();

            if(bpmndi == null) {
                Alert.show("获取BPMN DI数据失败！");
                return;
            }

            var node:XML;
//            var notation:BPMNotation;

            processProcessNode(processNodeList);

            // 读取collaboration participant
            var participantList:XMLList = processDefinitionXml.model::collaboration.*;
            var i:int;
            for(i = 0; i < participantList.length(); i++) {
                processDI(participantList[i]);
            }

            /*for(i = 0; i < diNodeArray.length; i++) {
                node = diNodeArray[i].node;
                var diNode:XML = diNodeArray[i].di;
                var startSequenceFlows:XMLList = diNodeArray[i].startSequenceFlows;
                var endSequenceFlows:XMLList = diNodeArray[i].endSequenceFlows;
                notation = new BPMNotation(node,  diNode, 15 - y_min, startSequenceFlows, endSequenceFlows); // 距离最上边15
                processNotationList.push(notation);
                canvas.addChild(notation);
                notation.addEventListener(NotationEvent.ICON_MOUSE_DOWN, onIconMouseDownHandler);
                notation.addEventListener(NotationEvent.ICON_MOUSE_UP, onIconMouseUpHandler);
                notation.addEventListener(NotationEvent.ICON_MOVE, onIconMoveHandler);
            }*/


            for(i = 0; i < diNodeArray.length; i++) {
                var notation:BaseNotation;
                node = diNodeArray[i].node;
                var diNode:XML = diNodeArray[i].di;
                var startSequenceFlows:XMLList = diNodeArray[i].startSequenceFlows;
                var endSequenceFlows:XMLList = diNodeArray[i].endSequenceFlows;
                var id:String = node.@id.toString();
                var name:String = node.@name.toString();
                var _x:Number, _y:Number, _width:Number, _height:Number;
                if (diNode != null) {
                    if ("BPMNShape" == diNode.localName()) {
                        _x = diNode.children()[0].@x;
                        _y = Number(diNode.children()[0].@y) + (15 -y_min); // 距离最上面15
                        _width = diNode.children()[0].@width;
                        _height = diNode.children()[0].@height;
                    }
                }

                if ("sequenceFlow" == node.localName()) {
                    notation = new SequenceFlowNotation();
                    NotationUtil.drawLine(notation.graphics, diNode, (15 - y_min));
                } else {
                    notation = getNotation(node.localName(), id, name, _x, _y, _width, _height);
                }
                if(notation != null) {
                    notation.highlight();
                    processNotationList.push(notation);
                    canvas.addChild(notation);
                    notation.addEventListener(NotationEvent.ICON_MOUSE_DOWN, onIconMouseDownHandler);
                    notation.addEventListener(NotationEvent.ICON_MOUSE_UP, onIconMouseUpHandler);
                    notation.addEventListener(NotationEvent.ICON_MOVE, onIconMoveHandler);
                }
            }
        }

        internal function processProcessNode(list:XMLList):void {
            for (var i:int = 0; i < list.length(); i++) {
                var node:XML = list[i];

                if("subProcess" == node.localName()) { // 读取子流程
                    processDI(node);
                    processProcessNode(node.children());
                } else if("laneSet" == node.localName()) { // 读取泳道
                    for(var j:int = 0; j < node.children().length(); j++) {
                        processDI(node.children()[j]);
                    }
                } else {
                    processDI(node);
                }
            }
        }

        internal function processDI(node:XML):void {
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
            var obj:Object = {};
            // 非sequenceFlow节点，获取此节点相关的sequenceFlow节点
            if(node.localName() != "sequenceFlow") {
                // 获取从此节点发出的sequenceFlow节点
                obj.startSequenceFlows = getNodeSequenceFlow(node, true);
                // 获取到此节点结束的sequenceFlow节点
                obj.endSequenceFlows = getNodeSequenceFlow(node, false);
            }
            obj.node = node;
            obj.di = diNode;
            diNodeArray.push(obj);
        }

        /**
         * 获取节点相关的sequenceFlow节点
         *
         * @param node 流程节点
         * @param isSource
         * @return
         */
        internal function getNodeSequenceFlow(node:XML, isSource:Boolean):XMLList {
            var xmlList:XMLList;
            if(isSource) {
                xmlList = processDefinitionXml.model::process..model::sequenceFlow.(@sourceRef == node.@id.toString());
                trace(node.@id.toString() + " start:   " + xmlList.length());
            } else {
                xmlList = processDefinitionXml.model::process..model::sequenceFlow.(@targetRef == node.@id.toString());
                trace(node.@id.toString() + " end:   " + xmlList.length());
            }

            return xmlList;
        }

        /**
         * 获取流程图符号
         *
         * @param type 流程图符号类型
         * @param id
         * @param _x
         * @param _height
         * @param name
         * @param _y
         * @param _width
         * @return 返回具体的流程图符号
         */
        public static function getNotation(type:String, id:String, name:String, _x:Number, _y:Number, _width:Number, _height:Number):BaseNotation {
            if ("startEvent" == type) {
                return new StartEventNotation(id, name, _x + 10, _y + 10, _width, _height);
            } else if ("endEvent" == type) {
                return new EndEventNotation(id, name,  _x + 10, _y + 10, _width, _height);
            } else if ("userTask" == type) {
                return new UserTaskNotation(id, name,  _x, _y, _width, _height);
            } else if ("exclusiveGateway" == type) {
                return new ExclusiveNotation(id, name,  _x, _y + _height, _width, _height);
            } else if ("parallelGateway" == type) {
                return new ParallelNotation(id, name,  _x, _y + _height, _width, _height);
            } else if ("serviceTask" == type) {
                return new ServiceTaskNotation(id, name,  _x, _y, _width, _height);
            } else if("lane" == type) {
                return new LaneNotation(id, name, _x, _y, _width, _height);
            } else if("participant" == type) {
                return new PoolNotation(id, name,  _x, _y, _width, _height);
            } else if("subProcess" == type) {
                return new SubProcessNotation(id, name,  _x, _y, _width, _height);
            } else if("boundaryEvent" == type) {

            }

            return null;
        }

        // 清空画布
        public function clearCanvas():void {
            canvas.removeAllChildren();
        }

        // 处理鼠标按下事件
        internal function onIconMouseDownHandler(event:NotationEvent):void {
            clearAllSelected();
            currentSelected = event.icon;
            currentSelected.selected();
            if(editable && currentSelected.dragable()) { // 开始拖动
                currentSelected.startDrag();
            }
        }

        // 处理鼠标弹起事件
        internal function onIconMouseUpHandler(event:NotationEvent):void {
            if(editable && currentSelected.dragable()) { // 停止拖动
                currentSelected.stopDrag();
            }
        }

        // 处理鼠标移动事件
        internal function onIconMoveHandler(event:NotationEvent):void {
            // TODO 拖动图标时重画与该图标相关的线条
            /*trace("mouseX = " + event.icon.x + ", mouseY = " + event.icon.y);
            var icon:BPMNotation = event.icon;
            icon.resetSequenceFlow();*/
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
                var processIcon:BaseNotation = processNotationList[i];
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
