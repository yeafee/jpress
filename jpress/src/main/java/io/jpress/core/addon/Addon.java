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

public class Addon {

	private String jarPath;
	private String addonClass;
	private String title;
	private String description;
	private String author;
	private String authorWebsite;
	private String version;
	private int versionCode;
	private String updateUrl;
	private boolean hasError = false;

	private IAddon addonImpl;
	private final Hooks hooks = new Hooks();

	public String getJarPath() {
		return jarPath;
	}

	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

	public String getAddonClass() {
		return addonClass;
	}

	public void setAddonClass(String addonClass) {
		this.addonClass = addonClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorWebsite() {
		return authorWebsite;
	}

	public void setAuthorWebsite(String authorWebsite) {
		this.authorWebsite = authorWebsite;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public IAddon getAddonImpl() {
		return addonImpl;
	}

	public void setAddonImpl(IAddon addonImpl) {
		this.addonImpl = addonImpl;
	}

	public Hooks getHooks() {
		return hooks;
	}
	
	public boolean getHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public void start() {
		if (addonImpl != null) {
			addonImpl.onStart(hooks);
		}
	}

	public void stop() {
		if (addonImpl != null) {
			addonImpl.onStop();
		}
	}

}
