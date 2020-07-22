const path = require('path');
module.exports = {
    apps:[{
        name:'operation_platform_web', // 应用名称
        script:"npm run start",
        watch: ['.next'],  // 监控变化的目录，一旦变化，自动重启
        ignore_watch:['node_modules','logs','public'],
        node_args: '--harmony', // node的启动模式
        env_production: {
            NODE_ENV: 'production',
            PORT: 8080
        },
        out_file: path.join(__dirname,'./logs/out.log'), // 普通日志路径
        error_file: path.join(__dirname,'./logs/error.log'), // 错误日志路径
        merge_logs: true,
        log_date_format: 'YYYY-MM-DD HH:mm Z',
        instances:"1" //负载均衡   根据机器CPU核数，开启对应数目的进程运行项目
    }]
}