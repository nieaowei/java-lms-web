module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                "syyles": "@assets/styles"
            }
        }
    },
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8081/',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true,
            },
            '/post':{
                target:'https://jsonplaceholder.typicode.com',
                pathRewrite: {
                    '^/post': ''
                },
                changeOrigin: true,
            }
        }
    }
}