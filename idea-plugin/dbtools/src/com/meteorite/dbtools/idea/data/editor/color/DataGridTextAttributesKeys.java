package com.meteorite.dbtools.idea.data.editor.color;

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.ui.SimpleTextAttributes;
import com.meteorite.dbtools.idea.common.ui.DBNColor;

import java.awt.*;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public interface DataGridTextAttributesKeys {
    Color DEFAULT_SELECTION_FG_COLOR = new Color(255, 255, 255);
    Color DEFAULT_SELECTION_BG_COLOR = new Color(82, 109, 165);

    interface Colors {
        Color DEFAULT_BACKGROUND = HighlighterColors.TEXT.getDefaultAttributes().getBackgroundColor();
        Color DEFAULT_FOREGROUND = HighlighterColors.TEXT.getDefaultAttributes().getForegroundColor();
        Color LIGHT_BACKGROUND = new DBNColor(new Color(0xf4f4f4), new Color(0x393939));
        Color LIGHT_FOREGROUND = new DBNColor(new Color(0xf4f4f4), new Color(0x999999));
        Color ERROR_BACKGROUND = HighlighterColors.BAD_CHARACTER.getDefaultAttributes().getBackgroundColor();
        Color PK_FOREGROUND = new DBNColor(new Color(0x4569A7), new Color(0x95A8B4));
        Color PK_BACKGROUND = new DBNColor(new Color(0xF7F7FF), new Color(0x2B3447));
        Color FK_FOREGROUND = new DBNColor(new Color(0x3F6B3F), new Color(0xA1A8A1));
        Color FK_BACKGROUND = new DBNColor(new Color(0xF7FFF7), new Color(0x2A3B2A));
        Color CARET_ROW_BACKGROUND = EditorColorsManager.getInstance().getGlobalScheme().getColor(EditorColors.CARET_ROW_COLOR);
    }

    interface Attributes {
        TextAttributes DEFAULT     = new TextAttributes(Colors.DEFAULT_FOREGROUND, Colors.DEFAULT_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
        TextAttributes DELETED     = new TextAttributes(Colors.LIGHT_FOREGROUND, Colors.LIGHT_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
        TextAttributes READONLY    = new TextAttributes(Colors.LIGHT_FOREGROUND, Colors.LIGHT_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
        TextAttributes ERROR       = new TextAttributes(Colors.DEFAULT_FOREGROUND, Colors.ERROR_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
        TextAttributes PRIMARY_KEY = new TextAttributes(Colors.PK_FOREGROUND,Colors.PK_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
        TextAttributes FOREIGN_KEY = new TextAttributes(Colors.FK_FOREGROUND, Colors.FK_BACKGROUND, null, null, SimpleTextAttributes.STYLE_PLAIN);
    }

    ColorKey CARET_ROW_BACKGROUND = ColorKey.createColorKey("DBTools.TextAttributes.DataEditor.CaretRowBackground", Colors.CARET_ROW_BACKGROUND);

    TextAttributesKey PLAIN_DATA     = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.PlainData",    Attributes.DEFAULT);
    TextAttributesKey MODIFIED_DATA  = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.ModifiedData", new TextAttributes(null, null, null, null, SimpleTextAttributes.STYLE_BOLD));
    TextAttributesKey DELETED_DATA   = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.DeletedData",  Attributes.DELETED);
    TextAttributesKey ERROR_DATA     = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.ErrorData",    Attributes.ERROR);
    TextAttributesKey READONLY_DATA  = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.ReadonlyData", Attributes.READONLY);
    TextAttributesKey LOADING_DATA   = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.LoadingData",  Attributes.READONLY);
    TextAttributesKey PRIMARY_KEY    = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.PrimaryKey",   Attributes.PRIMARY_KEY);
    TextAttributesKey FOREIGN_KEY    = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.ForeignKey",   Attributes.FOREIGN_KEY);
    TextAttributesKey SELECTION      = TextAttributesKey.createTextAttributesKey("DBNavigator.TextAttributes.DataEditor.Selection",    new TextAttributes(DEFAULT_SELECTION_FG_COLOR, DEFAULT_SELECTION_BG_COLOR, null, null, SimpleTextAttributes.STYLE_PLAIN));
}
