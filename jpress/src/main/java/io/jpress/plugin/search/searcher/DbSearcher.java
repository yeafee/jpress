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
package io.jpress.plugin.search.searcher;

import java.util.ArrayList;
import java.util.List;

import io.jpress.core.Jpress;
import io.jpress.model.Content;
import io.jpress.plugin.search.ISearcher;
import io.jpress.plugin.search.SearcherBean;

public class DbSearcher implements ISearcher {

	@Override
	public void init() {

	}

	@Override
	public void addBean(SearcherBean bean) {

	}

	@Override
	public void deleteBean(String beanId) {

	}

	@Override
	public void updateBean(SearcherBean bean) {

	}

	@Override
	public List<SearcherBean> search(String keyword) {
		return search(keyword, 1, 100);
	}

	@Override
	public List<SearcherBean> search(String keyword, int pageNum, int pageSize) {
		
		String[] modules = Jpress.currentTemplate().getModules().toArray(new String[] {});
		
		List<Content> list = Content.DAO.findListInNormal(pageNum, pageSize, "created DESC", keyword, null, null,
				modules, null, null, null, null, null, null,null);

		List<SearcherBean> datas = null;
		if (list != null) {
			datas = new ArrayList<SearcherBean>();
			for (Content c : list) {
				datas.add(new SearcherBean(c));
			}
		}
		return datas;
	}

}
