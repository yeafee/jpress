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
package io.jpress.model;

import io.jpress.core.db.Table;
import io.jpress.model.base.BaseOption;
import io.jpress.utils.StringUtils;

import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

@Table(tableName = "option", primaryKey = "id")
public class Option extends BaseOption<Option> {

	private static final long serialVersionUID = 1L;

	public static final Option DAO = new Option();

	public static final String KEY_WEB_NAME = "web_name";
	public static final String KEY_TEMPLATE_ID = "web_template_id";

	public static void saveOrUpdate(String key, String value) {
		Option option = DAO.doFindFirst("option_key =  ?", key);
		if (null == option) {
			option = new Option();
		}

		option.setOptionKey(key);
		option.setOptionValue(value);

		CacheKit.remove(CACHE_NAME, key);

		option.saveOrUpdate();
	}
	
	@Override
	public boolean saveOrUpdate() {
		CacheKit.remove(CACHE_NAME, getOptionKey());
		return super.saveOrUpdate();
	}

	public static String findValue(final String key) {
		String value = CacheKit.get(CACHE_NAME, key, new IDataLoader() {
			@Override
			public Object load() {
				Option option = DAO.doFindFirst("option_key =  ?", key);
				if (null != option && option.getOptionValue() != null) {
					return option.getOptionValue();
				}
				return "";
			}
		});
		
		return "".equals(value) ? null : value;
	}

	public Option findByKey(String key) {
		return DAO.doFindFirst("option_key =  ?", key);
	}

	public static Boolean findValueAsBool(String key) {
		String value = findValue(key);
		if (StringUtils.isNotBlank(value)) {
			try {
				return Boolean.parseBoolean(value);
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static Integer findValueAsInteger(String key) {
		String value = findValue(key);
		if (StringUtils.isNotBlank(value)) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static Float findValueAsFloat(String key) {
		String value = findValue(key);
		if (StringUtils.isNotBlank(value)) {
			try {
				return Float.parseFloat(value);
			} catch (Exception e) {
			}
		}
		return null;
	}

}
