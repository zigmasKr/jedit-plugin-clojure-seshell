/*
 * ClojureSEHandler.java
 * Copyright (c) 2007 Jakub Roztocil <jakub@webkitchen.cz>
 * Copyright (C) 2017 Zigmantas Kryzius <zigmas.kr@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package clojure.seshell;

//{{{ imports
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;
import org.gjt.sp.jedit.Macros.*;
//}}}

public class ClojureSEHandler extends Handler {

	//{{{ ClojureSEHandler constructors
	public ClojureSEHandler(String name) {
		super("ClojureSE");
	}//}}}

	//{{ accept method
	public boolean accept(String path) {
		return path.endsWith(".clj");
	}//}}}

	//{{{ creatMacro method
	public Macro createMacro(String macroName, String path) {
		String name = path.substring(0, path.length() - 4);
		return new Macro(this,
						name,
						Macro.macroNameToLabel(name),
						path);
	}//}}}

	//{{{ runMacro method
	public void runMacro(View view, Macro macro) {
		Log.log(Log.DEBUG, this, "runMacro " + macro.getPath());
		ClojureSEShell.runScript(macro.getPath(), view);
	}//}}}

	//{{{ evaluateCode method
	public ClojureSEShell.RetVal evaluateCode(View view, CharSequence command) {
		Log.log(Log.DEBUG, this, "evaluateCode");
		return ClojureSEShell.evaluateCode(view, command);
	}//}}}

	//{{{ getName method
	public String getName() {
		return "ClojureSEHandler";
	}//}}}

	//{{{ getLabel method
	public String getLabel() {
		return "Clojure script";
	}//}}}

}
/* :folding=explicit:collapseFolds=1:tabSize=4:indentSize=4:noTabs=false: */
