import request from "@/utils/request";

// 方法：手动构建参数对象（用于模糊查询）
const buildParams = (partId, partName, weight, color, startTime, endTime, isPublish) => {
  const params = {};
  if (partId) params.partId = partId;
  if (partName) params.partName = partName;
  if (weight) params.weight = weight;
  if (color) params.color = color;
  if (startTime) params.startTime = startTime;  // 假设startTime是已格式化的字符串
  if (endTime) params.endTime = endTime;
  params.isPublish = isPublish;
  return params;
};

export const getPartsList = (currentPage, pageSize) => { 
  return request.get(`/parts/getParts?currentPage=${currentPage}&pageSize=${pageSize}`);
}

export const likeParts = (partId, partName, weight, color, startTime, endTime, isPublish) => {
  return request.get(`/parts/likeParts`,
  {
    params: buildParams(partId, partName, weight, color, startTime, endTime, isPublish)
  });
}

export const updatePart = (part) => { 
  return request.put('/parts/updatePart', part);
}

export const deletePart = (id) => { 
  return request.delete(`/parts/deletePart/${id}`);
}

export const addPart = (part) => { 
  return request.post('/parts/addPart', part);
}

export const publishPart = (id,isPublish) => { 
  return request.put(`/parts/publishPart/${id}/${isPublish}`);
}