/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.service;

import java.util.List;
import java.util.Map;

import mblog.data.Config;

/**
 * @author langhsu
 *
 */
public interface ConfigService {
	/**
	 * 查询所有配置
	 * @return list
	 */
	List<Config> findAll();

	/**
	 * 查询所有配置
	 * @return map
	 */
	Map<String, Config> findAll2Map();

	/**
	 * 添加或修改配置
	 * - 修改时根据key判断唯一性
	 * @param configs
	 */
	void update(List<Config> configs);
}
