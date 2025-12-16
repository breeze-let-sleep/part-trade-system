import request from '@/utils/request'

export const getReportData = () => {
  return request.get('/report')
}