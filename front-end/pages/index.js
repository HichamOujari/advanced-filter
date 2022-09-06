import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import { useState } from 'react';


export default function Home() {

  const [listFields, setListFields] = useState([
    {
      name: 'email',
      operations: ['like', 'is equal to']
    },
    {
      name: 'grade',
      operations: ['is equal to'],
      propositions: ['lieutenant', 'sous-lieutenant', 'colonel major', 'commandant', 'capitaine']
    },
    {
      name: 'formation',
      operations: ['is equal to'],
      propositions: ['Cours d’application', 'Cours de capitaine', 'Cours d’état major', 'Ecole de guerre']
    },
    {
      name: 'anciennete',
      operations: ['is equal to', 'more then', 'less then']
    },
    {
      name: 'age',
      operations: ['is equal to', 'more then', 'less then'],
    },
    {
      name: 'diplome',
      operations: ['is equal to'],
      propositions: ['Ingénieur Informatique', 'Ingénieur Mécanique', 'Ingénieur civile', 'Ingénieur électrique']
    },
  ])

  const [listSelectedFields, setlistSelectedFields] = useState([]);
  return (
    <div className={styles.container}>
      <Head>
        <title>Generateur de requete</title>
        <meta name="description" content="Generateur de requete sql" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <p className={styles.title}>Generateur de requete</p>
        <div className={styles.conditions}>
          {listSelectedFields.map((ele, index) => (<div key={index} className={styles.condition}>
            <p className={styles.title}>{index == 0 ? 'WHERE' : 'AND'}</p>
            <select  onChange={(e) => {
              const value = e.target.value;
              const newTmp = listFields.filter(spele => spele.name === value)
              setlistSelectedFields([...newTmp,...listSelectedFields.filter(spele => spele.name!=ele.name)])
              setListFields([ele, ...listFields.filter(field => field.name != value)])
            }}>
              <option value={ele.name} hidden={true}>{ele.name}</option>
              {listFields.map((s_ele,index) => <option key={index} value={s_ele.name}>{s_ele.name}</option>)}
            </select>
            <select>
              {ele.operations.map((sp_ele,index) => (<option key={index} value={sp_ele}>{sp_ele}</option>))}
            </select>
            {
              ele.propositions ? <select>
                {ele.propositions.map((sp_ele,index) => (<option key={index} value={sp_ele}>{sp_ele}</option>))}
              </select> : <input type="text" placeholder="value" />
            }
            <p className={styles.removeClause} onClick={()=>{
              setlistSelectedFields([...listSelectedFields.filter(spele => spele.name != ele.name)])
              setListFields([ele,...listFields])
            }}>x</p>
          </div>))}
        </div>
        {listFields.length != 0 ? <button onClick={() => {
          const tmp = listFields[0];
          setlistSelectedFields([...listSelectedFields,tmp])
          setListFields([...listFields.filter(sp_ele => sp_ele.name != tmp.name)]);
        }}>Add clause</button> : ''}
        <button onClick={() => {
          console.log('listSelectedFields : ',listSelectedFields)
          console.log('listFields : ',listFields)
        }}>show</button>
      </main>
    </div>
  )
}
