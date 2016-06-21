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
package io.jpress.plugin.message.listener;

import java.math.BigInteger;
import java.util.List;

import io.jpress.Consts;
import io.jpress.model.Content;
import io.jpress.model.Taxonomy;
import io.jpress.plugin.message.Message;
import io.jpress.plugin.message.MessageAction;
import io.jpress.plugin.message.MessageListener;

public class MenuChangedListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		Object temp = message.getData();
		if (temp != null && (temp instanceof List<?>)) {

			@SuppressWarnings("unchecked")
			List<String> keys = (List<String>) temp;
			// 路由状态发生变化
			if (keys.contains("router_content_type") || keys.contains("router_fakestatic_enable")) {
				updateMenus();
			}
		}
	}

	private void updateMenus() {
		List<Content> list = Content.DAO.findByModule(Consts.MODULE_MENU, "order_number ASC");
		if (list != null && list.size() > 0) {
			for (Content content : list) {
				BigInteger taxonomyId = content.getObjectId();
				if (taxonomyId != null) {
					Taxonomy taxonomy = Taxonomy.DAO.findById(taxonomyId);
					if (taxonomy != null) {
						content.setText(taxonomy.getUrl());
						content.saveOrUpdate();
					}
				}
			}
		}
	}

	@Override
	public void onRegisterAction(MessageAction messageAction) {
		messageAction.register(Actions.SETTING_CHANGED);
	}

}
