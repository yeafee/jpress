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
package io.jpress.controller.admin;

import com.jfinal.aop.Before;

import io.jpress.core.JBaseController;
import io.jpress.interceptor.ActionCacheClearInterceptor;
import io.jpress.router.RouterMapping;
import io.jpress.router.RouterNotAllowConvert;

@RouterMapping(url = "/admin/tools", viewPath = "/WEB-INF/admin/tools")
@Before(ActionCacheClearInterceptor.class)
@RouterNotAllowConvert
public class _ToolsController extends JBaseController {

	public void index() {
		
	}
	
	public void druid() {
		
	}

	
}
