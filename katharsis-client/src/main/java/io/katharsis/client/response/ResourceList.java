package io.katharsis.client.response;

import java.util.List;

import io.katharsis.response.LinksInformation;
import io.katharsis.response.MetaInformation;
import io.katharsis.utils.WrappedList;

/**
 * Holds links and meta information next to the actual list.
 * 
 * Currently only the generic {@link JsonLinksInformation} and {@link JsonMetaInformation} are used. Support
 * for proper unmarshalling into application-specific meta and links objects will be supported in the future.
 * Support for  {@link JsonLinksInformation} and {@link JsonMetaInformation} will remain in the future
 * given that they are requested by {@link #getLinksInformation(Class)} or {@link #getMetaInformation(Class)}.
 */
public class ResourceList<T> extends WrappedList<T> {

	private LinksInformation links;

	private MetaInformation meta;

	public ResourceList(List<T> list, LinksInformation links, MetaInformation meta) {
		super(list);
		this.links = links;
		this.meta = meta;
	}

	public LinksInformation getLinksInformation() {
		return links;
	}

	public MetaInformation getMetaInformation() {
		return meta;
	}

	@SuppressWarnings("unchecked")
	public <L extends LinksInformation> L getLinksInformation(Class<L> linksClass) {
		if(linksClass != JsonLinksInformation.class){
			throw new IllegalArgumentException(linksClass.getName() + " not supported yet");
		}
		return (L) getLinksInformation();
	}

	@SuppressWarnings("unchecked")
	public <M extends MetaInformation> M getMetaInformation(Class<M> metaClass) {
		if(metaClass != JsonMetaInformation.class){
			throw new IllegalArgumentException(metaClass.getName() + " not supported yet");
		}
		return (M) getMetaInformation();
	}
}
