#!/usr/bin/env python

import yaml

SCALA_VERS = ['2.10.6', '2.11.8']
SPARK_VERS = ['2.0.2', '2.1.1', '2.2.0']
PYTHON_VERS = ['2.7', '3.5']
TENSORFLOW_VERS = ['1.3.0']

DIRECTORIES = [
    '$HOME/.ivy2/',
    '$HOME/.sbt/launchers/',
    '$HOME/.cache/spark-versions/',
]
DIRECTORIES += ['$HOME/.sbt/boot/scala-{}/'.format(ver) for ver in SCALA_VERS]

def is_python2(py_ver):
    return py_ver.startswith('2')


def get_tensorflow_env_conf(py_ver, tf_ver):
    env_conf = []
    base_format = "tensorflow-{tf_ver}-{py_conf}-linux_x86_64.whl"
    tf_py2_build = base_format.format(tf_ver=tf_ver, py_conf='cp27-none')
    tf_py3_build = base_format.format(tf_ver=tf_ver, py_conf='cp35-cp35m')
    env_conf.append('TF_PY2_BUILD={}'.format(tf_py2_build))
    env_conf.append('TF_PY3_BUILD={}'.format(tf_py3_build))
    if is_python2(py_ver):
        env_conf.append('TF_BUILD={}'.format(tf_py2_build))
    else:
        env_conf.append('TF_BUILD={}'.format(tf_py3_build))
    return ' '.join(env_conf)


def get_spark_env_conf(spark_ver):
    env_conf = []
    env_conf.append('SPARK_VERSION={}'.format(spark_ver))
    spark_build = "spark-{}-bin-hadoop2.7".format(spark_ver)
    env_conf.append('SPARK_BUILD="{}"'.format(spark_build))
    env_conf.append('SPARK_BUILD_URL="http://d3kbcqa49mib13.cloudfront.net/{}.tgz"'.format(spark_build))
    return ' '.join(env_conf)


def get_python_env_cof(py_ver):
    env_conf = []
    py_major = '2' if is_python2(py_ver) else '3'
    env_conf.append('PIP_BIN=pip{}'.format(py_major))
    env_conf.append('PYSPARK_PYTHON=python{}'.format(py_major))
    return ' '.join(env_conf)


ENV_MATRIX = []
for scala_ver in SCALA_VERS:
    for spark_ver in SPARK_VERS:
        for py_ver in PYTHON_VERS:
            for tf_ver in TENSORFLOW_VERS:
                ENV_MATRIX.append(' '.join([
                    'SCALA_BINARY_VERSION={}'.format(scala_ver),
                    get_spark_env_conf(spark_ver),
                    get_python_env_cof(py_ver),
                    get_tensorflow_env_conf(py_ver=py_ver, tf_ver=tf_ver),
                ]))


class TravisConfig(object):
    def __init__(self, **kwargs):
        self.namespace = kwargs

    def dump(self):
        print(yaml.dump(self.namespace, default_flow_style=False))


travis_yaml_config = TravisConfig(
    jdk='oraclejdk8',
    sudo='required',
    dist='trusty',
    cache={'directories': DIRECTORIES},
    env={'matrix': ENV_MATRIX}
)

travis_yaml_config.dump()
