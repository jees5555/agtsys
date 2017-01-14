package com.github.jees5555.agtsys.util;

public class MySqlPageTool {
	private Integer index; // 页码
	private Integer size = 5; // 每页条数
	private Integer totalCount;// 记录总数
	private Integer totalPages;// 总页数

	private Integer start; // mysql数据库的偏移量

	public MySqlPageTool(Integer index, Integer size) throws Exception {
		if (index == null) {
			throw new Exception("page index is null");
		}
		this.index = index;
		if (size != null) {
			this.size = size;
		}
		// 计算第一条记录的偏移位置
		this.start = (this.index - 1) * size;
	}

	// 根据总条数计算总页数
	public Integer getTotalPages(Integer totalCount) throws Exception {
		this.totalCount = totalCount;
		if (totalCount == null) {
			throw new Exception("totalCount is null");
		}
		this.totalPages = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
		return this.totalPages;
	}

	// 获取分页偏移
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStart() {
		return start;
	}

}
