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

import io.jpress.controller.front.ContentController;
import io.jpress.controller.front.IndexController;
import io.jpress.controller.front.TaxonomyController;

public class HookInvoker {

	public static String routerConverte(String target, HttpServletRequest request, HttpServletResponse response) {
		return (String) AddonManager.get().invokeHook("routerConverte", request, response);
	}

	public static Render processController(AddonController controller) {
		return (Render) AddonManager.get().invokeHook("processController", controller);
	}

	public static Boolean intercept(Invocation inv) {
		return (Boolean) AddonManager.get().invokeHook("intercept", inv);
	}

	public static void indexRenderBefore(IndexController controller) {
		AddonManager.get().invokeHook("indexRenderBefore", controller);
	}

	public static void indexRenderAfter(IndexController controller) {
		AddonManager.get().invokeHook("indexRenderAfter", controller);
	}
	
	public static void taxonomyRenderBefore(TaxonomyController controller) {
		AddonManager.get().invokeHook("taxonomyRenderBefore", controller);
	}

	public static void taxonomyRenderAfter(TaxonomyController controller) {
		AddonManager.get().invokeHook("taxonomyRenderAfter", controller);
	}
	
	public static void contentRenderBefore(ContentController controller) {
		AddonManager.get().invokeHook("contentRenderBefore", controller);
	}
	
	public static void contentRenderAfter(ContentController controller) {
		AddonManager.get().invokeHook("contentRenderAfter", controller);
	}

}
