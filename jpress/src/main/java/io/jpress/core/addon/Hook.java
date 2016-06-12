/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.core.addon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Invocation;
import com.jfinal.render.Render;

/**
 * Hook的定义，插件钩子都要继承此类。 同时，此类的方法是从HookInvoker复制过来的，保证方法名和参数完全一致。
 * 
 * @author michael
 */
public class Hook {

	private ThreadLocal<Boolean> tl = new ThreadLocal<Boolean>();

	protected void nextInvoke() {
		tl.set(true);
	}

	/**
	 * 子类不要调用此方法
	 */
	public void hookInvokeFinished() {
		tl.remove();
	}

	/**
	 * 子类不要调用此方法
	 */
	public boolean letNextHookInvoke() {
		return tl.get() != null && tl.get() == true;
	}

	public static final String HOOK_ROUTER_CONVERTE = "routerConverte";
	public static final String HOOK_PROCESS_CONTROLLER = "processController";
	public static final String HOOK_INTERCEPT = "intercept";

	public String routerConverte(String target, HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	public Render processController(AddonController controller) {

		return null;
	}
	
	public static Boolean intercept(Invocation inv) {
		//do nothing
		return null;
	}

}
