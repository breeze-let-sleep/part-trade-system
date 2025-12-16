import request from "@/utils/request";

// 方法：手动构建参数对象（用于模糊查询）
const buildParams = (input, startTime, endTime) => {
  const params = {};
  if (input) params.input = input;
  if (startTime) params.startTime = startTime;  // 假设startTime是已格式化的字符串
  if (endTime) params.endTime = endTime;
  return params;
};

export const getInfoById = (id) => { 
  return request.get(`/admins/getInfo/${id}`);
}

export const updateInfo = (admin) => { 
  console.log(admin);
  return request.put('/admins/updateAdmin', admin);
}

export const deleteAdmin = (id) => { 
  return request.delete(`/admins/deleteAdmin/${id}`);
}

export const getPageList = (currentPage, pageSize) => { 
  return request.get(`/admins/getAdmins?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const addAdmin = (admin) => { 
  return request.post('/admins/addAdmin', admin);
}

export const LikeSearch = (input, startTime, endTime) => {
  return request.get(`/admins/likeAdmins`,
  {
    params: buildParams(input, startTime, endTime)
  }
  ); 
}