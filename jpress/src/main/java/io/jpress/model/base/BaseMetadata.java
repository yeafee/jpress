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
package io.jpress.model.base;

import io.jpress.core.JModel;
import io.jpress.model.Metadata;

import java.util.List;
import java.math.BigInteger;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 *  Auto generated by JPress, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMetadata<M extends BaseMetadata<M>> extends JModel<M> implements IBean {

	public static final String CACHE_NAME = "metadata";
	public static final String METADATA_TYPE = "metadata";

	public void removeCache(Object key){
		CacheKit.remove(CACHE_NAME, key);
	}

	public void putCache(Object key,Object value){
		CacheKit.put(CACHE_NAME, key, value);
	}

	public M getCache(Object key){
		return CacheKit.get(CACHE_NAME, key);
	}

	public M getCache(Object key,IDataLoader dataloader){
		return CacheKit.get(CACHE_NAME, key, dataloader);
	}

	public Metadata findMetadata(String key){
		return Metadata.findByTypeAndIdAndKey(METADATA_TYPE, getId(), key);
	}

	public List<Metadata> findMetadataList(){
		return Metadata.findListByTypeAndId(METADATA_TYPE, getId());
	}

	public M findFirstFromMetadata(String key,Object value){
		Metadata md = Metadata.findFirstByTypeAndValue(METADATA_TYPE, key, value);
		if(md != null){
			BigInteger id = md.getObjectId();
			return findById(id);
		}
		return null;
	}

	public Metadata createMetadata(){
		Metadata md = new Metadata();
		md.setObjectId(getId());
		md.setObjectType(METADATA_TYPE);
		return md;
	}

	public Metadata createMetadata(String key,String value){
		Metadata md = new Metadata();
		md.setObjectId(getId());
		md.setObjectType(METADATA_TYPE);
		md.setMetaKey(key);
		md.setMetaValue(value);
		return md;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null){ return false; }
		if(!(o instanceof BaseMetadata<?>)){return false;}

		BaseMetadata<?> m = (BaseMetadata<?>) o;
		if(m.getId() == null){return false;}

		return m.getId().compareTo(this.getId()) == 0;
	}

	public void setId(java.math.BigInteger id) {
		set("id", id);
	}

	public java.math.BigInteger getId() {
		Object id = get("id");
		if (id == null)
			return null;

		return id instanceof BigInteger ? (BigInteger)id : new BigInteger(id.toString());
	}

	public void setMetaKey(java.lang.String metaKey) {
		set("meta_key", metaKey);
	}

	public java.lang.String getMetaKey() {
		return get("meta_key");
	}

	public void setMetaValue(java.lang.String metaValue) {
		set("meta_value", metaValue);
	}

	public java.lang.String getMetaValue() {
		return get("meta_value");
	}

	public void setObjectType(java.lang.String objectType) {
		set("object_type", objectType);
	}

	public java.lang.String getObjectType() {
		return get("object_type");
	}

	public void setObjectId(java.math.BigInteger objectId) {
		set("object_id", objectId);
	}

	public java.math.BigInteger getObjectId() {
		return get("object_id");
	}

}
