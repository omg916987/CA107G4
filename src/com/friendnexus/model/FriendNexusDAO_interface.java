package com.friendnexus.model;

import java.util.List;

   public interface FriendNexusDAO_interface {
	public void insert(FriendNexusVO friendNexusVO);

//	public void update(FriendNexusVO friendNexusVO);

	public FriendNexusVO findByPrimaryKey(String memId);

//	public void delete(String memId);

	public List<FriendNexusVO> getAll();
}
