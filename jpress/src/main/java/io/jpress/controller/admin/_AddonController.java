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

import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import io.jpress.core.JBaseController;
import io.jpress.core.addon.Addon;
import io.jpress.core.addon.AddonManager;
import io.jpress.interceptor.ActionCacheClearInterceptor;
import io.jpress.router.RouterMapping;
import io.jpress.router.RouterNotAllowConvert;
import io.jpress.utils.StringUtils;

@RouterMapping(url = "/admin/addon", viewPath = "/WEB-INF/admin/addon")
@Before(ActionCacheClearInterceptor.class)
@RouterNotAllowConvert
public class _AddonController extends JBaseController {

	public void index() {
		keepPara();
		
		AddonManager.get().reload();
		
		setAttr("addons", AddonManager.get().getAddons());
		setAttr("addonCount", AddonManager.get().getAddons().size());
		setAttr("startedAddonCount", AddonManager.get().getStartedAddons().size());
	}

	public void install() {
		keepPara();
		
		if (!isMultipartRequest()) {
			return;
		}

		UploadFile ufile = getFile();
		if (ufile == null) {
			renderAjaxResultForError("您还未选择插件文件");
			return;
		}

		String webRoot = PathKit.getWebRootPath();

		StringBuilder newFileName = new StringBuilder(webRoot).append("/WEB-INF/addons/").append(ufile.getFileName());

		File newfile = new File(newFileName.toString());

		if (newfile.exists()) {
			renderAjaxResultForError("该插件已经安装！");
			return;
		}

		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}

		ufile.getFile().renameTo(newfile);

		renderAjaxResultForSuccess();
	}

	public void uninstall() {
		keepPara();

		String id = getPara("id");
		if (!StringUtils.isNotBlank(id)) {
			renderAjaxResultForError();
			return;
		}

		Addon addon = AddonManager.get().findById(id);
		if (addon == null) {
			renderAjaxResultForError();
			return;
		}

		if(AddonManager.get().uninstall(addon)){
			renderAjaxResultForSuccess();
		}else{
			renderAjaxResultForError();
		}
		
	}

	public void start() {
		keepPara();

		String id = getPara("id");
		if (!StringUtils.isNotBlank(id)) {
			renderAjaxResultForError();
			return;
		}

		Addon addon = AddonManager.get().findById(id);
		if (addon == null) {
			renderAjaxResultForError();
			return;
		}

		if (AddonManager.get().start(addon)) {
			renderAjaxResultForSuccess();
		} else {
			renderAjaxResultForError();
		}

	}

	public void stop() {
		keepPara();

		String id = getPara("id");
		if (!StringUtils.isNotBlank(id)) {
			renderAjaxResultForError();
			return;
		}

		Addon addon = AddonManager.get().findById(id);
		if (addon == null) {
			renderAjaxResultForError();
			return;
		}

		if (AddonManager.get().stop(addon)) {
			renderAjaxResultForSuccess();
		} else {
			renderAjaxResultForError();
		}
	}

}
