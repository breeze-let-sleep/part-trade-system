import request from "@/utils/request";

// 方法：手动构建参数对象（用于模糊查询）
const buildParams = (likeName, likeAddress, startTime, endTime) => {
  const params = {};
  if (likeName) params.likeName = likeName;
  if (likeAddress) params.likeAddress = likeAddress;
  if (startTime) params.startTime = startTime;  // 假设startTime是已格式化的字符串
  if (endTime) params.endTime = endTime;
  return params;
};

export const getInfoById = (id) => { 
  return request.get(`/merchants/getInfo/${id}`);
}

export const updateInfo = (merchant) => { 
  return request.put('/merchants/updateMerchant', merchant);
}

export const deleteMerchant = (id) => { 
  return request.delete(`/merchants/deleteMerchant/${id}`);
}

export const getPageList = (currentPage, pageSize) => { 
  return request.get(`/merchants/getMerchants?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const addMerchants = (merchant) => { 
  return request.post('/merchants/addMerchant', merchant);
}

export const LikeSearch = (likeName, likeAddress, startTime, endTime) => {
  return request.get(`/merchants/likeMerchants`,
  {
    params: buildParams(likeName, likeAddress, startTime, endTime)
  }); 
}