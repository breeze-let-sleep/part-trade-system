import request from "@/utils/request";

// 方法：手动构建参数对象（用于模糊查询）
const buildParams = (likeName, likeAddress, startTime, endTime) => {
  const params = {};
  if (likeName) params.likeName = likeName;
  if (likeAddress) params.likeAddress = likeAddress;
  if (startTime) params.startTime = startTime;  
  if (endTime) params.endTime = endTime;
  return params;
};

export const getInfoById = (id) => { 
  return request.get(`/customers/getInfo/${id}`);
}

export const updateInfo = (customer) => { 
  return request.put('/customers/updateCustomer', customer);
}

export const deleteCustomer = (id) => { 
  return request.delete(`/customers/deleteCustomer/${id}`);
}

export const getPageList = (currentPage, pageSize) => { 
  return request.get(`/customers/getCustomers?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const addCustomer = (customer) => { 
  return request.post('/customers/addCustomer', customer);
}

export const LikeSearch = (likeName, likeAddress, startTime, endTime) => {
  return request.get(`/customers/likeCustomers`,
  {
    params: buildParams(likeName, likeAddress, startTime, endTime)
  }); 
}