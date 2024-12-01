package com.webz.Second.Hand.Shop.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public abstract class PageResultsImpl<E> implements PageResults<E> {
    private List<E> results;
    private Integer pageIndex;
    private Integer pageSize;
    private Long totalElements;

//    public PageResultsImpl() {
//    }

//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof PageResultsImpl)) {
//            return false;
//        } else {
//            PageResultsImpl<?> other = (PageResultsImpl) o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else {
//                Object this$pageIndex = this.getPageIndex();
//                Object other$pageIndex = other.getPageIndex();
//                if (this$pageIndex == null) {
//                    if (other$pageIndex != null) {
//                        return false;
//                    }
//                } else if (!this$pageIndex.equals(other$pageIndex)) {
//                    return false;
//                }
//
//                Object this$pageSize = this.getPageSize();
//                Object other$pageSize = other.getPageSize();
//                if (this$pageSize == null) {
//                    if (other$pageSize != null) {
//                        return false;
//                    }
//                } else if (!this$pageSize.equals(other$pageSize)) {
//                    return false;
//                }
//
//                Object this$totalElements = this.getTotalElements();
//                Object other$totalElements = other.getTotalElements();
//                if (this$totalElements == null) {
//                    if (other$totalElements != null) {
//                        return false;
//                    }
//                } else if (!this$totalElements.equals(other$totalElements)) {
//                    return false;
//                }
//
//                Object this$results = this.getResults();
//                Object other$results = other.getResults();
//                if (this$results == null) {
//                    if (other$results != null) {
//                        return false;
//                    }
//                } else if (!this$results.equals(other$results)) {
//                    return false;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof PageResultsImpl;
//    }
//
//    public int hashCode() {
//        int PRIME = 59;
//        int result = 1;
//        Object $pageIndex = this.getPageIndex();
//        result = result * 59 + ($pageIndex == null ? 43 : $pageIndex.hashCode());
//        Object $pageSize = this.getPageSize();
//        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
//        Object $totalElements = this.getTotalElements();
//        result = result * 59 + ($totalElements == null ? 43 : $totalElements.hashCode());
//        Object $results = this.getResults();
//        result = result * 59 + ($results == null ? 43 : $results.hashCode());
//        return result;
//    }

    public String toString() {
        List var10000 = this.getResults();
        return "PageResultsImpl(results=" + var10000 + ", pageIndex=" + this.getPageIndex() + ", pageSize=" + this.getPageSize() + ", totalElements=" + this.getTotalElements() + ")";
    }
}
